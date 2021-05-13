package com.nero.moviereview.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.nero.moviereview.R
import com.nero.moviereview.utils.formatHourMinutes
import com.nero.moviereview.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_single_movie_detail.*

@AndroidEntryPoint
class SingleMovieDetailFragment : Fragment(R.layout.fragment_single_movie_detail),
    RatingBar.OnRatingBarChangeListener {

    lateinit var navController: NavController
    private val viewModel: MovieViewModel by viewModels()

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getInt("movieId")!!
        val posterUrl = arguments?.getString("url")!!
        viewModel.singleMovieDetails(movieId).observe(viewLifecycleOwner, Observer { it ->
            Glide.with(ivPoster).load(posterUrl).into(ivPoster)

            tvTitle.text = it.data?.originalTitle
            textReleaseDate.text = it.data?.releaseDate
            tvDuration.text = it.data?.runtime?.formatHourMinutes()
            textOverview.text = it.data?.overview
            rbRating.rating = (it.data?.voteAverage?.toFloat()!!.div(2) ?: 0.0f)
            tvVoteCount.text = it.data.voteCount.toString()
            rbRating.onRatingBarChangeListener = this


        })

    }

    override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {
        rbRating.numStars
    }


}

