package com.duv.tokenflix.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.duv.tokenflix.FILM_ID
import com.duv.tokenflix.R
import com.duv.tokenflix.data.FilmRepository
import com.duv.tokenflix.model.FilmListModel
import kotlinx.android.synthetic.main.fragment_film_detail.*
import kotlinx.android.synthetic.main.fragment_film_detail.pb_loading
import kotlinx.android.synthetic.main.fragment_film_list.*

class FilmDetailFragment: Fragment(), FilmDetailView {

    private val presenter = FilmDetailPresenter(this, FilmRepository() )
    private var filmId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_film_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            filmId = it.getInt(FILM_ID)
        }
        presenter.getFilm(filmId)
    }

    override fun initFilm(film: FilmListModel) {
        presenter.getPoster(film.poster_url, iv_poster)
        tv_average_vote.text = film.vote_average.toString()
        tv_release_date.text = film.release_date
        tv_title.text = film.title
        film.genres.let {
            var filmGenres = ""
            for ( film in it){
                filmGenres += "$film "
            }
            tv_genres.text = filmGenres
        }
        pb_loading.visibility = View.INVISIBLE
    }

}