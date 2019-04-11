package com.example.asus.a03042019.model

import com.google.gson.annotations.SerializedName


class Movies {
    @SerializedName("poster_path")
    private lateinit var posterPath: String
    @SerializedName("adult")
    private var adult: Boolean = false
    @SerializedName("overview")
    private lateinit var overview: String
    @SerializedName("release_date")
    private var releaseDate: String? = null
    @SerializedName("genre_ids")
    private var genreIds = ArrayList<Int>()
    @SerializedName("id")
    private var id: Int? = null
    @SerializedName("original_title")
    private var originalTitle: String? = null
    @SerializedName("original_language")
    private var originalLanguage:String?=null
    @SerializedName("title")
    private var title: String? = null
    @SerializedName("backdrop_path")
    private var backdropPath: String? = null
    @SerializedName("popularity")
    private var popularity: Double? = null
    @SerializedName("vote_count")
    private var voteCount: Int? = null
    @SerializedName("video")
    private var video: Boolean = false
    @SerializedName("vote_average")
    private var voteAverage: Double? = null


    fun getPosterPath(): String? {
        return posterPath
    }

    fun setPosterPath(posterPath: String) {
        this.posterPath = posterPath
    }

    fun isAdult(): Boolean {
        return adult
    }

    fun setAdult(adult: Boolean) {
        this.adult = adult
    }

    fun getOverview(): String? {
        return overview
    }

    fun setOverview(overview: String) {
        this.overview = overview
    }

    fun getReleaseDate(): String? {
        return releaseDate
    }

    fun setReleaseDate(releaseDate: String) {
        this.releaseDate = releaseDate
    }

    fun getGenreIds(): List<Int> {
        return genreIds
    }

    fun setGenreIds(genreIds: ArrayList<Int>) {
        this.genreIds = genreIds
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getOriginalTitle(): String? {
        return originalTitle
    }

    fun setOriginalTitle(originalTitle: String) {
        this.originalTitle = originalTitle
    }

    fun getOriginalLanguage(): String? {
        return originalLanguage
    }

    fun setOriginalLanguage(originalLanguage: String) {
        this.originalLanguage = originalLanguage
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getBackdropPath(): String? {
        return backdropPath
    }

    fun setBackdropPath(backdropPath: String) {
        this.backdropPath = backdropPath
    }

    fun getPopularity(): Double? {
        return popularity
    }

    fun setPopularity(popularity: Double?) {
        this.popularity = popularity
    }

    fun getVoteCount(): Int? {
        return voteCount
    }

    fun setVoteCount(voteCount: Int?) {
        this.voteCount = voteCount
    }

    fun getVideo(): Boolean {
        return video
    }

    fun setVideo(video: Boolean) {
        this.video = video
    }

    fun getVoteAverage(): Double? {
        return voteAverage
    }

    fun setVoteAverage(voteAverage: Double?) {
        this.voteAverage = voteAverage
    }
}
