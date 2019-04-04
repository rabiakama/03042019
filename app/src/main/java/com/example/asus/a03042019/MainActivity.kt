package com.example.asus.a03042019

import android.R.attr.maxRows
import android.app.PendingIntent.getActivity
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import android.widget.Toast
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import com.example.asus.a03042019.adapter.MoviesAdapter
import com.example.asus.a03042019.model.Movies
import com.example.asus.a03042019.model.UpcomingMoviesResponse
import com.example.asus.a03042019.service.Api
import com.example.asus.a03042019.service.Api.Companion.API_KEY
import com.example.asus.a03042019.service.Api.Companion.DEFAULT_LANGUAGE
import com.example.asus.a03042019.service.Api.Companion.DEFAULT_REGION
import com.example.asus.a03042019.service.Client
import okhttp3.*
import java.io.IOException
import com.example.asus.a03042019.model.MoviesResponse
import android.support.v7.widget.GridLayoutManager
import android.R.attr.orientation
import android.view.Menu
import android.support.v7.widget.DefaultItemAnimator
import android.content.ContextWrapper
import android.app.Activity
import android.content.Context
import android.os.Parcelable




class MainActivity : AppCompatActivity() {
    private var recylerview: RecyclerView? = null
    private var moviesInstance = ArrayList<Movies>()
    private var savedRecyclerLayoutState: Parcelable? = null
    private val LIST_STATE: String = "list_state"
    private val BUNDLE_RECYCLER_LAYOUT = "recycler_layout"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recylerview = findViewById(R.id.recyclerView_main) as RecyclerView
        recylerview!!.layoutManager = GridLayoutManager(this, 2)


        fun getActivity(): Activity? {
            var context: Context = this
            while (context is ContextWrapper) {
                if (context is Activity) {
                    return context
                }
                context = (context).baseContext
            }
            return null

        }

        fun loadJson() {
            var apiService: Api
            val Client = Client
            apiService = Client.getClient()!!.create(Api::class.java)
            val call = apiService.getUpcomingMovies(API_KEY, DEFAULT_LANGUAGE, 1, DEFAULT_REGION)

            //       apiService.getUpcomingMovies(API_KEY, DEFAULT_LANGUAGE, 1, DEFAULT_REGION)
            call.enqueue(object : retrofit2.Callback<UpcomingMoviesResponse> {
                override fun onResponse(
                    call: retrofit2.Call<UpcomingMoviesResponse>,
                    response: retrofit2.Response<UpcomingMoviesResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        var movies: List<Movies> = response.body()?.results!!
                        moviesInstance.addAll(movies)
                        recylerview!!.setAdapter(MoviesAdapter(applicationContext, movies))
                        recylerview!!.smoothScrollToPosition(0)

                    }
                }

                override fun onFailure(call: retrofit2.Call<UpcomingMoviesResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }


            })
        }

        private fun displayData(movieModels: List<Movies>?) {
            val adapter = MoviesAdapter(this, movieModels!!)

            rows.setAdapter(adapter)
        }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.menu, menu)
            return super.onCreateOptionsMenu(menu)
        }


    }
}






