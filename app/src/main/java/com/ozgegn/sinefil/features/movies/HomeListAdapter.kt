package com.ozgegn.sinefil.features.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ozgegn.sinefil.R
import com.ozgegn.sinefil.data.MovieModel
import com.ozgegn.sinefil.databinding.ItemHomeMoviesListBinding

class HomeListAdapter(
    private val movieClickListener: MovieClickListener
) : ListAdapter<MovieModel, HomeListAdapter.HomeListViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListViewHolder {
        return HomeListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HomeListViewHolder, position: Int) {
        val movie = getItem(position)
        holder.onBindView(movie, movieClickListener)
    }

    class HomeListViewHolder(private val binding: ItemHomeMoviesListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindView(movieModel: MovieModel, movieClickListener: MovieClickListener) {
            with(binding) {
                movie = movieModel
                clickListener = movieClickListener
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): HomeListViewHolder {
                val binding = DataBindingUtil.inflate<ItemHomeMoviesListBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_home_movies_list,
                    parent,
                    false
                )
                return HomeListViewHolder(binding)
            }
        }
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

class MovieClickListener(val clickListener: (movie: MovieModel) -> Unit) {
    fun onClick(movieModel: MovieModel) = clickListener(movieModel)
}