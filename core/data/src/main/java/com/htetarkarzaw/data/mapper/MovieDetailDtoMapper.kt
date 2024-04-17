package com.htetarkarzaw.data.mapper

import com.htetarkarzaw.data.local.entity.DetailEntity
import com.htetarkarzaw.data.remote.dto.detail.MovieDetailDto

fun MovieDetailDto.toEntity() = DetailEntity(
    id = id,
    adult = adult ?: false,
    backdropPath = backdropPath ?: "",
    budget = budget ?: 0,
    homepage = homepage ?: "",
    imdbId = imdbId ?: "",
    originalLanguage = originalLanguage ?: "",
    originalTitle = originalTitle ?: "",
    overview = overview ?: "",
    popularity = popularity ?: 0.0,
    posterPath = posterPath ?: "",
    releaseDate = releaseDate ?: "",
    revenue = revenue ?: 0,
    runtime = runtime ?: 0,
    status = status ?: "",
    tagline = tagline ?: "",
    title = title ?: "",
    video = video ?: false,
    voteAverage = voteAverage ?: 0.0,
    voteCount = voteCount ?: 0,
    genres = genres?.map { it.name }?.joinToString(separator = " * ")?:"",
    genre = genres?.firstOrNull()?.name?:""
)