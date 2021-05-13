package com.nero.moviereview.views.nav_ui.movie.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.nero.moviereview.R
import com.nero.moviereview.data.model.Result
import com.nero.moviereview.data.remote.MovieClickListener

class MovieAdapter(
    private val itemClickListener: MovieClickListener
) :
    PagingDataAdapter<Result, MovieViewHolder>(diffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.movies_item_layout, parent, false)
        return MovieViewHolder(
            view, itemClickListener
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.setData(it) }
    }


    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(
                oldItem: Result,
                newItem: Result
            ): Boolean {
                return newItem == oldItem
            }

            override fun areContentsTheSame(
                oldItem: Result,
                newItem: Result
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }


}
