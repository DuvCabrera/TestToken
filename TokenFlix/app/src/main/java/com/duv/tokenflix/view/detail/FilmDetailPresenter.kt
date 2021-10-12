package com.duv.tokenflix.view.detail


import android.content.Context
import android.widget.ImageView
import com.duv.tokenflix.BASE_URL
import com.duv.tokenflix.FILM_PREFER
import com.duv.tokenflix.data.FilmRepository
import com.duv.tokenflix.model.FilmListModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmDetailPresenter(
    private val view: FilmDetailView,
    private val filmRepository: FilmRepository,
    private val context: Context?) {

    private val callback = object : Callback<FilmListModel>{
        override fun onResponse(
            call: Call<FilmListModel>,
            response: Response<FilmListModel>) {
            if (response.code() == 200) {
                val filmResponse = response.body()!!
                view.initFilm(filmResponse)
            }else {
                view.showError()
            }
        }

        override fun onFailure(call: Call<FilmListModel>, t: Throwable) {
            view.showError()
        }
    }

    fun getFilm(id: Int){
        filmRepository.getFilmApi(BASE_URL, callback, id)
    }

    fun getPoster(url: String, view: ImageView){
        Picasso.get().load(url).into(view)
    }

    fun saveSharedPreferences(json: FilmListModel ){
        val gson = Gson()
        val jsonString = gson.toJson(json)
        val sharedPref = context?.getSharedPreferences(FILM_PREFER, Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()

        editor?.apply {
            putString("films", jsonString)
            apply()
        }
    }

    fun getSharedPreferFilm(){
        val jsonString = context?.getSharedPreferences(FILM_PREFER, Context.MODE_PRIVATE)?.getString("films", null)
        val gson = Gson()
        val film =
        view.initFilm(film)
    }
}