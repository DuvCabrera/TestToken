package com.duv.tokenflix.view.detail

import com.duv.tokenflix.model.FilmListModel

interface FilmDetailView {

    fun initFilm(film: FilmListModel) {}

    fun showError() {}
}