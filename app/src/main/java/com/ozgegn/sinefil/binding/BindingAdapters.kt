package com.ozgegn.sinefil.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ozgegn.sinefil.R
import com.ozgegn.sinefil.data.GenreModel
import com.ozgegn.sinefil.data.MovieModel
import com.ozgegn.sinefil.data.ProviderModel
import com.ozgegn.sinefil.features.search.HomeListAdapter
import com.ozgegn.sinefil.features.search.SearchGenreListAdapter
import com.ozgegn.sinefil.features.service.StreamServicesAdapter
import java.text.SimpleDateFormat

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MovieModel>?) {
    val adapter = recyclerView.adapter as HomeListAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
}

@BindingAdapter("loadImage")
fun bindImageView(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context).load(url).placeholder(R.drawable.image_placeholder)
            .error(R.drawable.broken_image).into(imageView)
    }
}

@BindingAdapter("doubleText")
fun bindDoubleToText(textView: TextView?, value: Double?) {
    textView?.text = String.format("%.2f", value)
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

@BindingAdapter("visibilityValue")
fun bindViewVisibility(view: View?, isHidden: Boolean) {
    if (isHidden)
        view?.visibility = View.GONE
    else
        view?.visibility = View.VISIBLE
}

@BindingAdapter("loadProviders")
fun bindProviderRecyclerView(recyclerView: RecyclerView?, data: List<ProviderModel>?) {
    val adapter = recyclerView?.adapter as StreamServicesAdapter
    adapter.submitList(data) {
        recyclerView?.scrollToPosition(0)
    }
}