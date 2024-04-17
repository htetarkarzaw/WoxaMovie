package com.htetarkarzaw.movie.now_showing

sealed interface NowShowingAction {
    data class Navigate(val id: Int) : NowShowingAction
}