package com.example.asus.a03042019


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
import android.os.Parcelable
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.widget.SearchView
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import com.example.asus.a03042019.service.Api
import com.example.asus.a03042019.service.Client
import java.lang.Exception



class MainActivity : AppCompatActivity() {


    private val POPULAR_TASK = 0
    private val UPCOMING_TASK = 1
    private val TOP_RATED_TASK = 2
    //private val NOW_PLAYING_TASK = 3
    private val MOVIES = 4
    private var moviesAdapter: MoviesAdapter?=null
    private var arraylistmovies: ArrayList<Movies> = arrayListOf()
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository= Repository(Client.getClient()!!.create(Api::class.java))
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
        recylerView_main.adapter=moviesAdapter
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
        loadJson()
    }


    private fun fetchMovies(item: Int): Boolean {

        recylerView_main.visibility = View.INVISIBLE
        recylerView_main.visibility = View.VISIBLE
        when (item) {
            MOVIES -> {
                repository.getMovies()
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
            repository.getMovies().enqueue(object:Callback<MoviesResponse>{
                override fun onResponse(call: Call<MoviesResponse>, response: retrofit2.Response<MoviesResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        arraylistmovies.addAll(response.body()!!.results!!)
                        recylerView_main.layoutManager =
                            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                       moviesAdapter=MoviesAdapter(arraylistmovies)
                        recylerView_main.adapter = moviesAdapter
                        recylerView_main.smoothScrollToPosition(0)
                    }

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


















