package com.htetarkarzaw.domain.mapper

enum class MovieType {
    POPULAR, UPCOMING
}

fun MovieType.type(): String {
    return when (this) {
        MovieType.POPULAR -> "popular"
        MovieType.UPCOMING -> "upcoming"
    }
}