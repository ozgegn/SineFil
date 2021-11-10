package com.ozgegn.sinefil.features.movies

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ozgegn.sinefil.data.MovieModel
import com.ozgegn.sinefil.features.MovieClickListener
import com.ozgegn.sinefil.features.MovieViewHolder

class MoviesPagingAdapter(
    private val movieClickListener: MovieClickListener
) : PagingDataAdapter<MovieModel, MovieViewHolder>(DiffCallback) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.onBindView(movie, movieClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }


    companion object DiffCallback : DiffUtil.ItemCallback<MovieModel>() {

        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }
    }
}