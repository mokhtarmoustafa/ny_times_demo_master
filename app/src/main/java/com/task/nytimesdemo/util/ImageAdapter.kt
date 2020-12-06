package com.task.nytimesdemo.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.task.nytimesdemo.R

class ImageAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["profileImage", "error", "isCircleCrop"], requireAll = false)
        fun loadImage(view: ImageView, profileImage: String, error: Int, isCircleCrop: Boolean) {
            if (isCircleCrop) { // Rounded Image
                Glide.with(view.context)
                    .load(profileImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .circleCrop()
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            view.setImageResource(error)
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            view.setImageDrawable(resource)
                            return false
                        }
                    })

                    .into(view)
            } else { // DefaultImage
                Glide.with(view.context)
                    .load(profileImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .centerCrop()
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            view.setImageResource(error)
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            view.setImageDrawable(resource)
                            return false
                        }
                    })

                    .into(view)
            }


        }
    }
}