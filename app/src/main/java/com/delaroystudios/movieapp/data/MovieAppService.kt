package com.delaroystudios.movieapp.data

import com.delaroystudios.movieapp.data.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieAppService {

    companion object {
        const val ENDPOINT = "http://api.themoviedb.org/3/"
    }

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String?, @Query("page") page: Int? = null): Response<MoviesResponse>

}