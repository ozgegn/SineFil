package com.ozgegn.sinefil.features.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ozgegn.sinefil.R
import com.ozgegn.sinefil.data.GenreModel
import com.ozgegn.sinefil.databinding.ItemSearchGenresBinding

class SearchGenreListAdapter(
    val genreClickListener: GenreClickListener
) : ListAdapter<GenreModel, SearchGenreListAdapter.SearchGenreListViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchGenreListViewHolder {
        return SearchGenreListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchGenreListViewHolder, position: Int) {
        holder.onBindView(getItem(position), genreClickListener)
    }

    class SearchGenreListViewHolder(val binding: ItemSearchGenresBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindView(genreModel: GenreModel, genreClickListener: GenreClickListener) {
            with(binding) {
                genre = genreModel
                clickListener = genreClickListener
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): SearchGenreListViewHolder {
                val binding = DataBindingUtil.inflate<ItemSearchGenresBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_search_genres,
                    parent,
                    false
                )
                return SearchGenreListViewHolder(binding)
            }
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<GenreModel>() {
        override fun areItemsTheSame(oldItem: GenreModel, newItem: GenreModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GenreModel, newItem: GenreModel): Boolean {
            return oldItem == newItem
        }
    }

}

class GenreClickListener(val clickListener: (genre: GenreModel) -> Unit) {
    fun onClick(genreModel: GenreModel) = clickListener(genreModel)
}