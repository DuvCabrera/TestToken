package com.duv.tokenflix.view.list

import com.duv.tokenflix.model.FilmListModel

interface FilmListView {

    fun initFilmList(list: List<FilmListModel>) { }

    fun showError() { }

    fun onClickListener(id: Int) { }
}