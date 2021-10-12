package com.duv.tokenflix.view.list

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.duv.tokenflix.BASE_URL
import com.duv.tokenflix.FILM_PREFER
import com.duv.tokenflix.data.FilmRepository
import com.duv.tokenflix.model.FilmListModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmListPresenter (
    private val view: FilmListView,
    private val filmRepository: FilmRepository,
    private val context: Context?
) {

    private val callback = object : Callback<List<FilmListModel>> {
        override fun onResponse(
            call: Call<List<FilmListModel>>,
            response: Response<List<FilmListModel>>
        ) {
            if (response.code() == 200) {
                val filmResponse = response.body()!!
                saveSharedPreferences(filmResponse)
                view.initFilmList(filmResponse)
            } else {
                view.showError()
            }
        }

        override fun onFailure(call: Call<List<FilmListModel>>, t: Throwable) {
            view.showError()
        }
    }

    fun getFilmList(){
        filmRepository.getApiList(BASE_URL, callback)
    }

    fun getPoster(url: String, view: ImageView){
        Picasso.get().load(url).into(view)
    }

    private fun saveSharedPreferences(json: List<FilmListModel> ){
        val gson = Gson()
        val jsonString = gson.toJson(json)
        val sharedPref = context?.getSharedPreferences(FILM_PREFER, Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()

        editor?.apply {
            putString("films", jsonString)
            apply()
        }
    }

    fun tranformaJson(json:String?) :List<FilmListModel>{
        val type = object: TypeToken<List<FilmListModel>>(){ }.type
        return Gson().fromJson(json, type)
    }

    fun getSharedPreferFilm(){
        val jsonString = context?.getSharedPreferences(FILM_PREFER, Context.MODE_PRIVATE)?.getString("films", null)
        val filmList = tranformaJson(jsonString)
        Log.e("RESPOSTA GSON", filmList.toString())
        view.initFilmList(filmList)
    }
}