package com.nero.moviereview.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.nero.moviereview.data.model.MovieResponse
import com.nero.moviereview.data.model.singleMovieResponseModel.SingleMovieResponse
import com.nero.moviereview.data.remote.MovieClient
import com.nero.moviereview.utils.Constants.API_KEY
import com.nero.moviereview.utils.Constants.START_PAGE
import com.nero.moviereview.utils.Resource
import com.nero.moviereview.views.nav_ui.movie.pagination.MoviePagingSource
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieClient: MovieClient) {


    suspend fun getMovies(): Resource<MovieResponse> {
        val response = try {
            movieClient.movieList(API_KEY, START_PAGE)
        } catch (e: Exception) {
            return Resource.Error("${e.localizedMessage}")
        }
        return Resource.Success(response)
    }

    suspend fun getSingleMovieDetails(movie_id: Int): Resource<SingleMovieResponse> {
        val response = try {
            movieClient.singleMovieDetail(movie_id)
        } catch (e: Exception) {
            return Resource.Error("${e.localizedMessage}")

        }

//        Log.d("TAG", response.toString())
        return Resource.Success(response)

    }

    fun getSearchResults() =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(movieClient) }
        ).liveData

}