package com.htetarkarzaw.movie.upcoming

sealed interface UpcomingAction {
    data class Navigate(val id: Int) : UpcomingAction
}