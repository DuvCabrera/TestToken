package com.duv.tokenflix.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.duv.tokenflix.FILM_ID
import com.duv.tokenflix.R
import com.duv.tokenflix.data.FilmRepository
import com.duv.tokenflix.model.FilmListModel
import com.duv.tokenflix.view.list.adapter.FilmListAdapter
import kotlinx.android.synthetic.main.fragment_film_list.*

class FilmListFragment: Fragment(), FilmListView {

    private val presenter: FilmListPresenter = FilmListPresenter(this, FilmRepository())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_film_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getFilmList()
    }

    override fun initFilmList(list: List<FilmListModel>) {
        val adapter = FilmListAdapter(list, this, presenter)
        rv_list.layoutManager= GridLayoutManager(context,2)
        rv_list.adapter = adapter
        pb_loading.visibility = View.INVISIBLE
    }

    override fun onClickListener(id: Int) {
        findNavController().navigate(R.id.action_fragmentFilmList_to_fragmentFilmDetail, Bundle().apply {
            putInt(FILM_ID, id)
        })
    }

    override fun showError() {
        Toast.makeText(context, "Ops, ocorreu algum erro.", Toast.LENGTH_SHORT).show()
    }

}