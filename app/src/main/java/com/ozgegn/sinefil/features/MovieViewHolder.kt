package com.ozgegn.sinefil.features

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ozgegn.sinefil.R
import com.ozgegn.sinefil.data.MovieModel
import com.ozgegn.sinefil.databinding.ItemHomeMoviesListBinding

class MovieViewHolder(private val binding: ItemHomeMoviesListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBindView(movieModel: MovieModel, movieClickListener: MovieClickListener) {
        with(binding) {
            movie = movieModel
            clickListener = movieClickListener
            executePendingBindings()
        }
    }

    companion object {
        fun from(parent: ViewGroup): MovieViewHolder {
            val binding = DataBindingUtil.inflate<ItemHomeMoviesListBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_home_movies_list,
                parent,
                false
            )
            return MovieViewHolder(binding)
        }
    }
}

class MovieClickListener(val clickListener: (movie: MovieModel) -> Unit) {
    fun onClick(movieModel: MovieModel) = clickListener(movieModel)
}