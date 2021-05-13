package com.nero.moviereview.views.nav_ui.movie.pagination


import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nero.moviereview.data.model.Result
import com.nero.moviereview.data.remote.MovieClient
import com.nero.moviereview.utils.Constants.API_KEY
import com.nero.moviereview.utils.Constants.START_PAGE


class MoviePagingSource(
    private val movieClient: MovieClient
) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val postion = params.key ?: START_PAGE
        Log.d("hello","asd" )
        return try {
            val response = movieClient.movieList(API_KEY, postion)
            LoadResult.Page(
                data = response.results!!,
                prevKey = if (postion == START_PAGE) null else postion - 1,
                nextKey = if (response.results.isEmpty()) null else postion + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}