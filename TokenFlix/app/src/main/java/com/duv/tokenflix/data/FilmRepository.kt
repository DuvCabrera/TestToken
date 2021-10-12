package com.duv.tokenflix.data

import android.content.Context
import android.content.SharedPreferences
import com.duv.tokenflix.model.FilmListModel
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FilmRepository {

    private fun createRetrofit(baseUrl: String): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiList(baseUrl: String, callback: Callback<List<FilmListModel>>){

        val retrofit = createRetrofit(baseUrl)

        val service = retrofit.create(TokenFlixHolderApi::class.java)
        val call = service.getFilmList()
        call.enqueue(callback)
    }

    fun getFilmApi(baseUrl: String, callback: Callback<FilmListModel>, id: Int){

        val retrofit = createRetrofit(baseUrl)

        val service = retrofit.create(TokenFlixHolderApi::class.java)
        val call = service.getFilm(id)
        call.enqueue(callback)
    }

    fun getSharedPreferFilm(context: Context?, key: String) : SharedPreferences?{
        return context?.getSharedPreferences(key, Context.MODE_PRIVATE)

    }
}