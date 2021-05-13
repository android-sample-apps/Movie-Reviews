package com.nero.moviereview.views.nav_ui.movie

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.nero.moviereview.R
import com.nero.moviereview.data.model.Result
import com.nero.moviereview.viewModel.MovieViewModel
import com.nero.moviereview.data.remote.MovieClickListener
import com.nero.moviereview.views.nav_ui.movie.rv.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie), MovieClickListener {


    private var movieList = mutableListOf<Result>()
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var navController: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        val LlManager = GridLayoutManager(requireContext(),3)
        rvMovies.layoutManager = LlManager
        movieAdapter = MovieAdapter(this)
        viewModel.movie.observe(viewLifecycleOwner, Observer {
             viewLifecycleOwner.lifecycleScope.launch {
                movieAdapter.submitData(it)

            }
        })
        rvMovies.adapter = movieAdapter



        viewModel.movie().observe(viewLifecycleOwner, Observer {
            movieList.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })
    }

    override fun onMovieClicked(movieResponse: Int?, moviePoster: String) {
        val bundle = bundleOf("movieId" to movieResponse, "url" to moviePoster)
        navController.navigate(R.id.action_nav_movie_to_singleMovieDetailFragment, bundle)

    }
}