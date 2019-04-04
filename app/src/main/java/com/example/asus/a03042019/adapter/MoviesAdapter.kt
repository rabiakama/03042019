package com.example.asus.a03042019.adapter

import android.content.Context
import com.example.asus.a03042019.model.Movies
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.asus.a03042019.R
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter (var context: Context, var movielist:List<Movies>): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(movielist[position])
    }

    fun setMovies(movie: List<Movies>) {
        movielist = movie
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return movielist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)

        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titletxt by lazy { itemView.findViewById<TextView>(R.id.titleTextView) }
        //val posterpath by lazy { itemView.findViewById<ImageView>(R.id.img_thumbnail) }
        val genretxt by lazy { itemView.findViewById<TextView>(R.id.genresTextView) }
        val releasetxt by lazy { itemView.findViewById<TextView>(R.id.releaseDateTextView) }
        val posterpath: String =
            "https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg"

        fun bindTo(mv: Movies) {

            titletxt.text = mv.getTitle()
            genretxt.text = mv.getVoteAverage().toString()
            releasetxt.text = mv.getVoteCount().toString()

            Glide.with(context)
                .load(posterpath)
                .thumbnail(Glide.with(itemView.context).load(R.drawable.abc_ic_go_search_api_material))
                .into(itemView.posterImageView)

        }


    }
}