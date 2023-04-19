package id.rafli.baseproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.rafli.baseproject.R
import id.rafli.baseproject.adapter.holder.NewsHolder
import id.rafli.baseproject.helper.Go
import id.rafli.baseproject.model.News
import id.rafli.baseproject.ui.newsDetail.NewsDetailActivity

class NewsAdapter(var data:ArrayList<News> = ArrayList()): RecyclerView.Adapter<NewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        return  NewsHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_news, parent, false))
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.item.tvTitle.rootView.setOnClickListener {
            Go(holder.context).move(NewsDetailActivity::class.java, id = data[position].id)
        }

        holder.setData(data[position])
    }

    fun  addList(datas:List<News>) {
        data.addAll(datas)
        notifyItemRangeInserted(data.size - datas.size, datas.size)
    }

    override fun getItemCount(): Int {
        return data.size
    }


}