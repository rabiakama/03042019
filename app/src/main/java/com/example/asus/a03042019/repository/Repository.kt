package com.example.asus.a03042019.repository

import com.example.asus.a03042019.model.Movies

import com.example.asus.a03042019.service.Api




class Repository(private val api: Api) {


    fun getPopularMovies()=api.getPopularMovies(apiKey ="534bc4143a626777d62c7d1ab8697aba",language = "pt-BR",page = 0,region = "BR" )


    fun getMovies()=api.getMovie(apiKey = "534bc4143a626777d62c7d1ab8697aba",language = "en")

    fun getTopMovies() = api.getTopRatedMovies(apiKey ="534bc4143a626777d62c7d1ab8697aba",language = "pt-BR",page = 0,region = "BR")

    fun getComingMovies()=api.getUpcomingMovies(apiKey ="534bc4143a626777d62c7d1ab8697aba",language = "pt-BR",page = 0,region = "BR")


    fun searchMovie() = api.searchMovie(apiKey ="534bc4143a626777d62c7d1ab8697aba",language = "pt-BR",page = 0)



    //query:343611

    //fun favoriteMovies()=api.getFavoriteMovies()
}