package com.example.imagelistview.view.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.imagelistview.R
import com.example.imagelistview.view.model.ImageDataModel
import com.example.imagelistview.view.presentation.ImageListAdapter

fun ImageListAdapter.ImageListViewHolder.loadImageWithGlide(
    ctx: Context,
    itemImageDataModel: ImageDataModel?,
    loadingTimes: MutableList<Long> ?
) {
    val begin = System.currentTimeMillis()
    Glide.with(ctx)
        .load(itemImageDataModel?.imageUrl)
        .placeholder(R.drawable.loading_placeholer)
        .override(Target.SIZE_ORIGINAL)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                p0: GlideException?,
                p1: Any?,
                p2: com.bumptech.glide.request.target.Target<Drawable>?,
                p3: Boolean
            ): Boolean {
                Log.e(
                    "Image Loading Time",
                    "Image Loading Time of No: ${itemImageDataModel?.imageId} Failed"
                )
                return false
            }

            override fun onResourceReady(
                p0: Drawable?,
                p1: Any?,
                p2: com.bumptech.glide.request.target.Target<Drawable>?,
                p3: DataSource?,
                p4: Boolean
            ): Boolean {
                val end = System.currentTimeMillis()
                Log.d(
                    "Image Loading Time",
                    "Image Loading Time of No: ${itemImageDataModel?.imageId}: ${end - begin} milliseconds"
                )
                loadingTimes?.add(end - begin)
                return false
            }
        })
        .into(binding.simpleImgView)
}