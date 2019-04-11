package com.example.asus.a03042019.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Client {

    val BASE_URL = "http://api.themoviedb.org/3/"
    var retrofit: Retrofit? = null


    fun getClient(): Retrofit? {

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit
        }
}


//abstract class Client {
//    protected val api: Api = Retrofit.Builder()
//        .baseUrl(Api.URL)
//        .client(OkHttpClient.Builder().build())
//        .addConverterFactory(MoshiConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()
//        .create(Api::class.java)
//
//
//}