package com.nero.moviereview.views.nav_ui.movie.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nero.moviereview.data.model.Result
import com.nero.moviereview.data.remote.MovieClickListener
import kotlinx.android.synthetic.main.movies_item_layout.view.*

class MovieViewHolder(
    private val itemView: View,
    private val movieClickListener: MovieClickListener
) : RecyclerView.ViewHolder(itemView) {

    var moviePosterUrl =
        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"


    fun setData(movieResponse: Result) {
        itemView.apply {
            val moviePoster =
                moviePosterUrl + movieResponse.posterPath

            tvMovieName.text = movieResponse.originalTitle
            tvDate.text = movieResponse.releaseDate

            Glide.with(itemView.ivMoviePoster).load(moviePoster).into(itemView.ivMoviePoster)
            val review = movieResponse.voteAverage
            if (review != null) {
                circularProgressBar.progress = ((review * 10).toFloat())
            }
            tvProgress.text = (movieResponse.voteAverage?.times(10))?.toInt().toString()

            cvMovie.setOnClickListener {
                movieClickListener.onMovieClicked(movieResponse.id,moviePoster)
            }
        }
    }
}