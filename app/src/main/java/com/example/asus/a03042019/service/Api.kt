package com.example.asus.a03042019.service

import com.example.asus.a03042019.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface Api {
    companion object {
        const val URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "534bc4143a626777d62c7d1ab8697aba"
        const val DEFAULT_LANGUAGE = "pt-BR"
        const val DEFAULT_REGION = "BR"
    }

    @GET("genre/movie/list")
    fun genres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<GenresResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Long,
        @Query("region") region: String
    ): Call<UpcomingMoviesResponse>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Long,
        @Query("region") region: String
    ): Call<PopularMoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Long,
        @Query("region") region: String
    ): Call<TopRatedMoviesResponse>

    @GET("/account/{account_id}/favorite/movies")
    fun getFavoriteMovies(
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("page") page:Int
    ):Call<FavoriteMoviesResponse>

    @GET("movie/{id}")
    fun getMovie(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<MoviesResponse>
}
