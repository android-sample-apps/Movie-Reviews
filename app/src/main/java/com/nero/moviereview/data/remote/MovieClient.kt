package com.nero.moviereview.data.remote

import com.nero.moviereview.data.model.MovieResponse
import com.nero.moviereview.data.model.singleMovieResponseModel.SingleMovieResponse
import com.nero.moviereview.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieClient {

    @GET("movie/popular")
    suspend fun movieList(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun singleMovieDetail(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): SingleMovieResponse
}