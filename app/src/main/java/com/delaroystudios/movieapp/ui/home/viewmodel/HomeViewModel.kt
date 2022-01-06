package com.delaroystudios.movieapp.ui.home.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delaroystudios.movieapp.data.model.Movie
import com.delaroystudios.movieapp.data.model.MoviesResponse
import com.delaroystudios.movieapp.ui.home.repository.HomeRepository
import com.delaroystudios.movieapp.util.Resource
import com.delaroystudios.movieapp.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val moviePopular: MutableLiveData<Resource<MoviesResponse>> = MutableLiveData()
    var movieListResponse: MoviesResponse? = null
    var moviePage = 1

    /*fun fetchPopular(apikey: String){
        moviePopular.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                if (hasInternetConnection(context)) {
                    val response = homeRepository.fetchPopular(apikey)
                    moviePopular.postValue(Resource.Success(response.body()!!))
                } else
                    moviePopular.postValue(Resource.Error("No Internet Connection"))
            } catch (ex: Exception) {
                when (ex) {
                    is IOException -> moviePopular.postValue(Resource.Error("Network Failure " +  ex.localizedMessage))
                    else -> moviePopular.postValue(Resource.Error("Conversion Error"))
                }
            }
        }
    }
*/

    fun fetchPopular(apikey: String) = viewModelScope.launch {
        safeMovieCall(apikey, moviePage)
    }

    private suspend fun safeMovieCall(apikey: String, page: Int){
        moviePopular.postValue(Resource.Loading())
        try{
            if(hasInternetConnection(context)){
                val response = homeRepository.fetchPopular(apikey, page)
                moviePopular.postValue(handleOrderResponse(response))
            }
            else
                moviePopular.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> moviePopular.postValue(Resource.Error("Network Failure"))
                else -> moviePopular.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleOrderResponse(response: Response<MoviesResponse>): Resource<MoviesResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                moviePage++
                if (movieListResponse == null)
                    movieListResponse = resultResponse
                else {
                    val oldMovies = movieListResponse!!.movies as ArrayList<Movie>?
                    val newMovies = resultResponse.movies!! as ArrayList<Movie>?
                    oldMovies!!.addAll(newMovies!!)
                }
                return Resource.Success(movieListResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}