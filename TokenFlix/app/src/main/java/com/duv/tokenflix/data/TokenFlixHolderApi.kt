package com.duv.tokenflix.data

import com.duv.tokenflix.model.FilmListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TokenFlixHolderApi {

    @GET("movies")
    fun getFilmList(): Call<List<FilmListModel>>

    @GET("movies/{id}")
    fun getFilm(@Path("id") id: Int?): Call<FilmListModel>

}