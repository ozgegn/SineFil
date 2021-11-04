package com.ozgegn.sinefil.base

interface ItemViewModel {
    val viewType: Int
        get() = ItemViewType.NONE
}

object ItemViewType {
    const val NONE = 0
    const val POPULAR_MOVIES = 1
    const val NOW_PLAYING_MOVIES = 2
    const val TOP_RATED_MOVIES = 3
}