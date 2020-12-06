package com.task.nytimesdemo.ui.article

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.task.nytimesdemo.R
import com.task.nytimesdemo.data.Article
import com.task.nytimesdemo.databinding.ArticleRecyclerItemBinding


class ArticleAdapter :
    PagingDataAdapter<Article, ArticleAdapter.ArticleViewHolder>(ARTICLE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =
            ArticleRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            Log.d("position", "position: $position")
            holder.bind(currentItem)
        }
    }


    inner class ArticleViewHolder(private val binding: ArticleRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                navigateToArticleDetails(binding.article!!,it)

            }
        }

        fun bind(article: Article) {
            binding.apply {
                binding.article = article
                binding.llDate.setDate(article.published_date)


            }
        }
    }

    private fun navigateToArticleDetails(article: Article, view: View) {
        val action = ArticleFragmentDirections.actionArticleFragmentToArticleDetailFragment(article)
        view.findNavController().navigate(action)
    }


    companion object {
        private val ARTICLE_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Article, newItem: Article) =
                oldItem == newItem
        }
    }
}