package com.duv.tokenflix.view.list

import android.widget.ImageView
import com.duv.tokenflix.BASE_URL
import com.duv.tokenflix.data.FilmRepository
import com.duv.tokenflix.model.FilmListModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmListPresenter (private val view: FilmListView, private val filmRepository: FilmRepository) {

    private val callback = object : Callback<List<FilmListModel>> {
        override fun onResponse(
            call: Call<List<FilmListModel>>,
            response: Response<List<FilmListModel>>
        ) {
            if (response.code() == 200) {
                val filmResponse = response.body()!!
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

}