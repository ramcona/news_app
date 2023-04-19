package id.rafli.baseproject.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import id.rafli.baseproject.databinding.ItemNewsBinding
import id.rafli.baseproject.extention.getTimeAgo
import id.rafli.baseproject.extention.loadImage
import id.rafli.baseproject.model.News

class NewsHolder(var item:ItemNewsBinding): RecyclerView.ViewHolder(item.root) {

    val context = itemView.context

    fun setData(data: News) {
        item.tvTitle.text = data.title
        data.news_category?.let {
            item.tvCategory.text = it.title
        } ?: run {
            item.tvCategory.text = "-"
        }
        item.tvDate.text = data.createdAt.getTimeAgo()
        item.tvThumb.loadImage(data.thumb)

    }

}
