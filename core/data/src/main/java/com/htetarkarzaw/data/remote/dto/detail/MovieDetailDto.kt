package com.htetarkarzaw.data.remote.dto.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDto(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("adult")
    val adult: Boolean? = false,
    @SerialName("backdrop_path")
    val backdropPath: String? = "",
    @SerialName("budget")
    val budget: Int? = 0,
    @SerialName("homepage")
    val homepage: String? = "",
    @SerialName("imdb_id")
    val imdbId: String? = "",
    @SerialName("original_language")
    val originalLanguage: String? = "",
    @SerialName("original_title")
    val originalTitle: String? = "",
    @SerialName("overview")
    val overview: String? = "",
    @SerialName("popularity")
    val popularity: Double? = 0.0,
    @SerialName("poster_path")
    val posterPath: String? = "",
    @SerialName("release_date")
    val releaseDate: String? = "",
    @SerialName("revenue")
    val revenue: Int? = 0,
    @SerialName("runtime")
    val runtime: Int? = 0,
    @SerialName("status")
    val status: String? = "",
    @SerialName("tagline")
    val tagline: String? = "",
    @SerialName("title")
    val title: String? = "",
    @SerialName("video")
    val video: Boolean? = false,
    @SerialName("vote_average")
    val voteAverage: Double? = 0.0,
    @SerialName("vote_count")
    val voteCount: Int? = 0,


    @SerialName("genres")
    val genres: List<GenreDto>? = emptyList(),
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyDto>? = emptyList(),
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryDto>? = emptyList(),
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDto>? = emptyList(),
)