package com.delaroystudios.movieapp.ui.home.repository

import com.delaroystudios.movieapp.data.MovieAppService
import com.delaroystudios.movieapp.data.model.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val movieAppService: MovieAppService
) {

    suspend fun fetchPopular(apikey: String, page: Int? = null): Response<MoviesResponse> = withContext(
        Dispatchers.IO) {
        val popular = movieAppService.getPopularMovies(apikey, page)
        popular
    }

    suspend fun fetchTopRated(apikey: String, page: Int? = null): Response<MoviesResponse> = withContext(
        Dispatchers.IO) {
        val top = movieAppService.getTopRatedMovies(apikey, page)
        top
    }
}