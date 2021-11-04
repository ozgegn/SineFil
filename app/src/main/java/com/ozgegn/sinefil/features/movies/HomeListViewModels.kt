package com.ozgegn.sinefil.features.movies

import com.ozgegn.sinefil.base.ItemViewModel
import com.ozgegn.sinefil.base.ItemViewType
import com.ozgegn.sinefil.data.MovieModel

class PopularMoviesViewModel(val list: List<MovieModel>): ItemViewModel {
    override val viewType: Int
        get() = ItemViewType.POPULAR_MOVIES
}

class NowPlayingMoviesViewModel(val list: List<MovieModel>): ItemViewModel {
    override val viewType: Int
        get() = ItemViewType.NOW_PLAYING_MOVIES
}

class TopRatedMoviesViewModel(val list: List<MovieModel>): ItemViewModel {
    override val viewType: Int
        get() = ItemViewType.TOP_RATED_MOVIES
}