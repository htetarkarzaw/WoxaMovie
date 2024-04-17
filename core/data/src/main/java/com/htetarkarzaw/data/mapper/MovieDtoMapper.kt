package com.htetarkarzaw.data.mapper

import com.htetarkarzaw.data.local.entity.NowShowingEntity
import com.htetarkarzaw.data.local.entity.UpcomingEntity
import com.htetarkarzaw.data.remote.dto.movies.MovieDto

fun MovieDto.toNowShowingEntity(page: Int) = NowShowingEntity(
    id = id,
    adult = adult ?: false,
    backdropPath = backdropPath ?: "",
    originalLanguage = originalLanguage ?: "",
    originalTitle = originalTitle ?: "",
    overview = overview ?: "",
    popularity = popularity ?: 0.0,
    posterPath = posterPath ?: "",
    releaseDate = releaseDate ?: "",
    title = title ?: "",
    video = video ?: false,
    voteAverage = voteAverage ?: 0.0,
    voteCount = voteCount ?: 0,
    page = page
)


fun MovieDto.toUpcomingEntity(page: Int) = UpcomingEntity(
    id = id,
    adult = adult ?: false,
    backdropPath = backdropPath ?: "",
    originalLanguage = originalLanguage ?: "",
    originalTitle = originalTitle ?: "",
    overview = overview ?: "",
    popularity = popularity ?: 0.0,
    posterPath = posterPath ?: "",
    releaseDate = releaseDate ?: "",
    title = title ?: "",
    video = video ?: false,
    voteAverage = voteAverage ?: 0.0,
    voteCount = voteCount ?: 0,
    page = page
)