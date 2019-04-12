package com.example.asus.a03042019.repository


import com.example.asus.a03042019.service.Api


class Repository(private val api: Api) {


    fun getPopularMovies()=api.getPopularMovies(apiKey =  "534bc4143a626777d62c7d1ab8697aba",language = "en-US",page = 1,region="TR")


    fun getMovies()=api.getMovie(language = "en-US",page = 1,apiKey = "534bc4143a626777d62c7d1ab8697aba")

    fun getTopMovies() = api.getTopRatedMovies(apiKey ="534bc4143a626777d62c7d1ab8697aba",language = "en-US",page = 1,region = "TR")

    fun getComingMovies()=api.getUpcomingMovies(apiKey ="534bc4143a626777d62c7d1ab8697aba",language = "en-US",page = 1,region = "TR")


    //fun searchMovie() = api.searchMovie(apiKey ="534bc4143a626777d62c7d1ab8697aba",language = "pt-BR",page = 1)



    //query:343611

}