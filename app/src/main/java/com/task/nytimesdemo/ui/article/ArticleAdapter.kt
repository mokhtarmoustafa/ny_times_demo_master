package com.task.nytimesdemo.ui.article

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
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


class ArticleAdapter(private val listener: OnItemClickListener) :
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
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(article: Article) {
            binding.apply {

                Glide.with(itemView)
                    .load( if (article.media.isNotEmpty())
                        article.media[0].media_metadata[2].url
                    else
                        "")
                    .error(R.drawable.ic_error)
                    .circleCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }
                    })
                    .into(image)

                binding.tvTitle.text = article.title
                binding.tvCreatedBy.text = article.byline
                binding.tvSource.text = article.source
                binding.llDate.date.text = article.published_date


            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(article: Article)
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