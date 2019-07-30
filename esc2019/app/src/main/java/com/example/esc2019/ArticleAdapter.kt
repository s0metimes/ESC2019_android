package com.example.esc2019

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

class ArticleAdapter(val context: Context, private val articles: ArrayList<Article>): RecyclerView.Adapter<ArticleAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false)

        return Holder(v)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(articles[position])
        holder.v!!.setOnClickListener {
            val intent = Intent(holder.v.context, ArticleReadActivity::class.java)
            intent.putExtra("pk", articles[position].pk)
            holder.v.context.startActivity(intent)
        }
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        private val tvTitle = itemView?.findViewById<TextView>(R.id.tv_title)
        private val tvContent = itemView?.findViewById<TextView>(R.id.tv_content)
        private val tvCreateDate = itemView?.findViewById<TextView>(R.id.tv_createdate)
        private val tvName = itemView?.findViewById<TextView>(R.id.tv_name)
        val v = itemView

        fun bind (article: Article) {
            tvTitle?.text = article.title
            tvContent?.text = article.content
            tvCreateDate?.text = article.createdate
            tvName?.text = article.name
        }
    }
}