package com.htetarkarzaw.data.remote

import com.htetarkarzaw.common.Endpoint
import com.htetarkarzaw.data.BuildConfig
import com.htetarkarzaw.data.remote.dto.detail.MovieDetailDto
import com.htetarkarzaw.data.remote.dto.movies.MoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET(Endpoint.NOW_SHOWING)
    suspend fun fetchNowShowingMovies(
        @Query("limit") size: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MoviesDto

    @GET(Endpoint.UPCOMING)
    suspend fun fetchUpcomingMovie(
        @Query("limit") size: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MoviesDto

    @GET(Endpoint.DETAIL)
    suspend fun getDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") key : String = BuildConfig.API_KEY
    ): Response<MovieDetailDto>
}