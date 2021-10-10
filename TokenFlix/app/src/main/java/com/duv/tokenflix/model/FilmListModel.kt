package com.duv.tokenflix.model

data class FilmListModel(
    val id: Int,
    val vote_average: Float,
    val title: String,
    val poster_url: String,
    val genres: List<String>,
    val release_date: String
)
