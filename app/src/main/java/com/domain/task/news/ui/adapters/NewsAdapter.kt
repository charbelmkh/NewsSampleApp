package com.domain.task.news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.domain.task.R
import com.domain.task.core.ui.views.loadImageFromUrl
import com.domain.task.core.utils.DateUtils
import com.domain.task.news.data.models.News
import java.lang.Exception


class NewsAdapter(private val onItemInterAction: OnItemInterAction,private val layout: Int) :ListAdapter<News, NewsAdapter.ViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.root.setOnClickListener {
            onItemInterAction.onRowClicked(item)
        }
        holder.bookmark.setOnClickListener {
            onItemInterAction.onAddToBookMarkClick(item)
        }
        holder.thumbnail.loadImageFromUrl(getImageUrl(item))
        holder.bookmark.isSelected=item.is_bookmark
        holder.title.text = item.title
        holder.byline.text = item.byline
        holder.releaseDate.text = DateUtils.formatDate(item.createdDate)
    }

    private fun getImageUrl(item: News): String {
        return try {
            item.multimedia[2].url
        }catch (e:Exception){
            ""
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root = view;
        val thumbnail: AppCompatImageView = view.findViewById(R.id.thumbnail)
        val title: AppCompatTextView = view.findViewById(R.id.title)
        val byline: AppCompatTextView = view.findViewById(R.id.byline)
        val bookmark: AppCompatImageView = view.findViewById(R.id.bookmark)
        val releaseDate: AppCompatTextView = view.findViewById(R.id.releaseDate)

    }


    private class DiffCallback : DiffUtil.ItemCallback<News>() {

        override fun areItemsTheSame(
            oldItem: News,
            newItem: News
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: News,
            newItem: News
        ): Boolean {
            return oldItem == newItem
        }
    }
}




