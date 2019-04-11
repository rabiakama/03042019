package com.example.asus.a03042019

import android.animation.Animator
import android.app.DownloadManager
import android.app.SearchManager
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.asus.a03042019.adapter.MoviesAdapter
import com.example.asus.a03042019.model.Movies
import com.example.asus.a03042019.repository.Repository
import android.view.View
import android.support.v7.widget.LinearLayoutManager
import com.example.asus.a03042019.model.MoviesResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import android.view.Gravity
import android.os.Build
import android.os.Parcelable
import android.transition.Slide
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.support.v7.widget.DefaultItemAnimator
import com.example.asus.a03042019.service.Api
import com.example.asus.a03042019.service.Client
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.lang.Exception


class MainActivity : AppCompatActivity() {


    private val POPULAR_TASK = 0
    private val UPCOMING_TASK = 1
    private val TOP_RATED_TASK = 2
    //private val NOW_PLAYING_TASK = 3
    private val SEARCH_TASK = 4
    private var moviesAdapter: MoviesAdapter? = null
    private var arraylistmovies: ArrayList<Movies> = arrayListOf()

    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {

            displayData()
        } else {
            initViews()
        }

    }

    private fun xx() {
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.popular_movies -> {
                    recylerView_main.smoothScrollToPosition(0)
                    fetchMovies(POPULAR_TASK)
                    true
                }
                R.id.top_rated_movies -> {
                    recylerView_main.smoothScrollToPosition(0)
                    fetchMovies(TOP_RATED_TASK)
                    true
                }
                R.id.upcoming_movies -> {
                    recylerView_main.smoothScrollToPosition(0)
                    fetchMovies(UPCOMING_TASK)
                    true
                }
                R.id.my_favorite_movies -> {
                    recylerView_main.smoothScrollToPosition(0)
                    //fetchFavMovies()
                    true
                }
                else -> fetchMovies(POPULAR_TASK)

            }
        }
    }


    private fun displayData() {

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recylerView_main.layoutManager = GridLayoutManager(this, 2)
        } else {
            recylerView_main.layoutManager = GridLayoutManager(this, 4)
        }
        recylerView_main.itemAnimator = DefaultItemAnimator()
        restoreLayoutManagerPosition()
        recylerView_main.adapter = moviesAdapter
        moviesAdapter?.notifyDataSetChanged()
    }

    private fun restoreLayoutManagerPosition() {
        val savedRecyclerLayoutState: Parcelable? = null
        if (savedRecyclerLayoutState != null) {
            recylerView_main.layoutManager?.onRestoreInstanceState(savedRecyclerLayoutState)
        }
    }

    private fun initViews() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recylerView_main.layoutManager = GridLayoutManager(this, 2)

        } else {
            recylerView_main.layoutManager = GridLayoutManager(this, 4)
        }
        recylerView_main.itemAnimator = DefaultItemAnimator()
        recylerView_main.adapter = moviesAdapter
        loadJson()
    }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//
//        menuInflater.inflate(R.menu.menu, menu)
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        val searchView = menu.findItem(R.id.search_view).actionView as SearchView
//
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        searchView.maxWidth = Int.MAX_VALUE
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                moviesAdapter?.filter?.filter(query)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                moviesAdapter?.filter?.filter(newText)
//                return true
//
//            }
//        })
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        val id = item?.itemId
//
//        return if (id == R.id.search_view) {
//            true
//        } else
//            return super.onOptionsItemSelected(item)
//    }
//
//    override fun onBackPressed() {
//        if (searchView.isIconified) {
//            searchView.isIconified = true
//        }
//        super.onBackPressed()
//    }
//
//    private fun onItemClicked() {
//        Toast.makeText(this@MainActivity,"you clicked",Toast.LENGTH_LONG).show()
//
//    }

    private fun fetchMovies(item: Int): Boolean {

        recylerView_main.visibility = View.INVISIBLE
        recylerView_main.visibility = View.VISIBLE
        when (item) {
            SEARCH_TASK -> {
                repository.searchMovie()
                return true
            }
            POPULAR_TASK -> {

                repository.getPopularMovies()
                return true
            }
            TOP_RATED_TASK -> {

                repository.getTopMovies()
                return true
            }
            UPCOMING_TASK -> {
                repository.getComingMovies()
                return true
            }
            //now playing eklenecek
            else -> return true
        }
    }

    private fun loadJson() {

        try {
            val apiInterface: Api = Client.getClient()!!.create(Api::class.java)
            val call: Call<MoviesResponse> =
                apiInterface.getMovie(apiKey = "534bc4143a626777d62c7d1ab8697aba", language = "en")
            call.enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(call: Call<MoviesResponse>, response: retrofit2.Response<MoviesResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        //var movies: List<Movies>? = response.body()?.results
                        arraylistmovies.addAll(response.body()!!.results)
                        recylerView_main.layoutManager =
                            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                        recylerView_main.adapter = moviesAdapter
                        recylerView_main.smoothScrollToPosition(0)
                    }
                    recylerView_main.visibility = View.VISIBLE
                    recylerView_main.visibility = View.INVISIBLE
                }

                override fun onFailure(call: retrofit2.Call<MoviesResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                    recylerView_main.visibility = View.INVISIBLE
                    recylerView_main.visibility = View.GONE
                }
            })
        } catch (e: Exception) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }
    }


















