package com.nero.moviereview.data.remote

interface MovieClickListener {
    fun onMovieClicked(movieResponse: Int?, moviePoster: String)
}
