package com.example.asus.a03042019

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.view.View
import com.example.asus.a03042019.model.Movies
import com.example.asus.a03042019.repository.Repository
import com.example.asus.a03042019.utils.clearLightStatusBar
import com.example.asus.a03042019.utils.getBackdropUrl
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.movie_item.*


class DetailActivity : AppCompatActivity() {

    private var imgbyte:Byte = 0
    private lateinit var tempMov:Movies
    private lateinit var mMovie:Movies
    private lateinit var repository:Repository

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        window.statusBarColor = ContextCompat.getColor(this,R.color.dark_gray)
        clearLightStatusBar(window.decorView)

        setSupportActionBar(detailToolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
            title=""
        }
//        details_back_drop.transitionName =getString(R.string.transition_name_details) + mMovie?.getId()
//        details_back_drop.loadImage(getBackdropUrl(mMovie.getBackdropPath()))

       // setMovieDetails(mMovie)

    }



   // private fun setMovieDetails(movie: Movies?) {
//
//
//        if(trailer !=null){
//            detailsPlayIv.visibility=View.VISIBLE
//            constraint.setOnClickListener{
//                val url= Uri.parse("https://www.youtube.com/watch?v=${trailer.key}")
//
//            startActivity(
//                Intent(Intent.ACTION_VIEW,url)
//            )
//        }}
//        else{
//            detailsPlayIv.visibility= View.GONE
//        }
    }


//}


