package com.htetarkarzaw.common


object Endpoint {
    // Feeds ( movies ) paging test
    const val MOVIES_BASE_URL = "https://api.themoviedb.org/3/"
    const val MOVIES_IMAGE_URL = "https://image.tmdb.org/t/p/original"
    private const val MOVIE = "movie/"
    const val NOW_SHOWING = "${MOVIE}now_playing"
    const val UPCOMING = "${MOVIE}upcoming"
    const val DETAIL = "${MOVIE}{movie_id}"
}