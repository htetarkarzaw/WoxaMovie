package com.htetarkarzaw.domain.mapper

import com.htetarkarzaw.data.local.entity.DetailEntity
import com.htetarkarzaw.domain.model.MovieDetail


fun DetailEntity.toVo() = MovieDetail(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    budget = budget,
    homepage = homepage,
    imdbId = imdbId,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    status = status,
    tagline = tagline,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
    genres = genres,
    genre = genre
)