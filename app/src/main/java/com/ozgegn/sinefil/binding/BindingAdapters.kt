package com.ozgegn.sinefil.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ozgegn.sinefil.R
import com.ozgegn.sinefil.data.GenreModel
import com.ozgegn.sinefil.data.MovieModel
import com.ozgegn.sinefil.features.movies.HomeListAdapter
import com.ozgegn.sinefil.features.search.SearchGenreListAdapter
import java.text.SimpleDateFormat

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MovieModel>?) {
    val adapter = recyclerView.adapter as HomeListAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
}

@BindingAdapter("loadImage")
fun bindImageView(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).placeholder(R.drawable.image_placeholder)
        .error(R.drawable.broken_image).into(imageView)
}

@BindingAdapter("doubleText")
fun bindDoubleToText(textView: TextView?, value: Double?) {
    textView?.text = String.format("%f", value)
}

@BindingAdapter("formatDate")
fun bindFormattedDate(textView: TextView?, date: String?) {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy")
    val inputDateFormat = SimpleDateFormat("yyyy-MM-dd")

    try {
        val parsedDate = inputDateFormat.parse(date)
        textView?.text = dateFormat.format(parsedDate)
    } catch (e: Exception) {
        textView?.text = date
    }
}

@BindingAdapter("genreListData")
fun bindGenreRecyclerView(recyclerView: RecyclerView, data: List<GenreModel>?) {
    val adapter = recyclerView.adapter as SearchGenreListAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
}