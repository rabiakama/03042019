package com.example.asus.a03042019.model

import com.squareup.moshi.Json

data class MoviesResponse (
    val movielist:List<Movies>? = null
)

data class UpcomingMoviesResponse(
    val page: Int,
    val results: List<Movies>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)
data class PopularMoviesResponse(
    val page: Int,
    val results: List<Movies>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)
data class TopRatedMoviesResponse(
    val page: Int,
    val results: List<Movies>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)
data class FavoriteMoviesResponse(
    val page: Int,
    val results: List<Movies>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)

