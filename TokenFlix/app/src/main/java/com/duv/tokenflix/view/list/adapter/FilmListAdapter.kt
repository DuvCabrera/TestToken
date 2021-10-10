package com.duv.tokenflix.view.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duv.tokenflix.R
import com.duv.tokenflix.model.FilmListModel
import com.duv.tokenflix.view.list.FilmListPresenter
import com.duv.tokenflix.view.list.FilmListView
import kotlinx.android.synthetic.main.film_list_item.view.*

class FilmListAdapter (private val data: List<FilmListModel>, private val listener: FilmListView, private val presenter: FilmListPresenter)
    :RecyclerView.Adapter<FilmListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.film_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItem(item: FilmListModel){
            itemView.tv_film_title.text = item.title
            presenter.getPoster(item.poster_url, itemView.image_film_poster)

            itemView.setOnClickListener{
                listener.onClickListener(item.id)
            }

        }
    }
}