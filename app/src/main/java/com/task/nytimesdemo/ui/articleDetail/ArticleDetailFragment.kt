package com.task.nytimesdemo.ui.articleDetail

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.task.nytimesdemo.MainActivity
import com.task.nytimesdemo.R
import com.task.nytimesdemo.databinding.FragmentArticleDetailBinding
import kotlinx.android.synthetic.main.article_recycler_item.*
import kotlinx.android.synthetic.main.fragment_article_detail.*

class ArticleDetailFragment : Fragment(R.layout.fragment_article_detail) {

    //region Members
    private val args by navArgs<ArticleDetailFragmentArgs>()
    //endregion

    //region Events
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArticleDetailBinding.bind(view)
        binding.apply {
            val article = args.article

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }



            Glide.with(this@ArticleDetailFragment)
                .load(article.media[0].media_metadata[2].url)
                .centerCrop()
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
                        createdBy.isVisible = true
                        descriptionDes.isVisible = article != null
                        return false
                    }
                })
                .into(imageView)

            Log.d("TAG", "onViewCreated: $article")
            article.title.let {  titleDes.text=it}
            article.abstract.let { descriptionDes.text=it }
            article.byline.let{ createdBy.text=it}
            article.source.let {  source.text=it }




        }

    }

    //endregion

}