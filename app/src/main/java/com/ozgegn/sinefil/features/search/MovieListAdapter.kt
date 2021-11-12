package com.ozgegn.sinefil.features.search

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ozgegn.sinefil.data.MovieModel
import com.ozgegn.sinefil.features.MovieClickListener
import com.ozgegn.sinefil.features.MovieViewHolder

class HomeListAdapter(
    private val movieClickListener: MovieClickListener
) : ListAdapter<MovieModel, MovieViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.onBindView(movie, movieClickListener)
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

