package com.nero.moviereview.viewModel

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.nero.moviereview.data.model.Result
import com.nero.moviereview.data.model.singleMovieResponseModel.SingleMovieResponse
import com.nero.moviereview.repository.MovieRepository
import com.nero.moviereview.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private var movieData = MutableLiveData<List<Result>>(listOf())

    fun movie(): MutableLiveData<List<Result>> {
        viewModelScope.launch {

            val result = movieRepository.getMovies()
            movieData.value = result.data?.results!!
        }
        return movieData
    }


    fun singleMovieDetails(id: Int): LiveData<Resource<SingleMovieResponse>> {
        return liveData(Dispatchers.IO) {
            val result = movieRepository.getSingleMovieDetails(id)
            emit(result)
        }
    }


    val movie = movieRepository.getSearchResults().cachedIn(viewModelScope)


}

