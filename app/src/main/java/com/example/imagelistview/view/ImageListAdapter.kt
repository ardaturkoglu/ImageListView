package com.example.imagelistview.view

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.imagelistview.R
import com.example.imagelistview.databinding.ItemSimpleImageBinding
import com.example.imagelistview.model.ImageDataModel
import com.example.imagelistview.model.ImageList


class ImageListAdapter(private val imageDataSet: ImageList?) :
    RecyclerView.Adapter<ImageListAdapter.ImageListViewHolder>() {

    val loadingTimes: MutableList<Long> = mutableListOf()

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ImageListViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val item = ItemSimpleImageBinding.inflate(layoutInflater, viewGroup, false)
        return ImageListViewHolder(item)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = imageDataSet?.imageList?.size ?: 0

    override fun onBindViewHolder(holder: ImageListViewHolder, position: Int) {
        holder.bindData(imageDataSet?.imageList?.getOrNull(position))
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class ImageListViewHolder(val binding: ItemSimpleImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(itemImage: ImageDataModel?) {
            binding.apply {
                imageName.text = itemImage?.imageId.toString()
                val begin = System.currentTimeMillis()
                Glide.with(this@ImageListViewHolder.itemView.context)
                    .load(itemImage?.imageUrl)
                    .placeholder(R.drawable.loading_placeholer)
                    .override(SIZE_ORIGINAL)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            p0: GlideException?,
                            p1: Any?,
                            p2: com.bumptech.glide.request.target.Target<Drawable>?,
                            p3: Boolean
                        ): Boolean {
                            Log.e(
                                "Image Loading Time",
                                "Image Loading Time of No: ${itemImage?.imageId} Failed"
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
                                "Image Loading Time of No: ${itemImage?.imageId}: ${end - begin} milliseconds"
                            )
                            loadingTimes.add(end-begin)
                            return false
                        }
                    })
                    .into(simpleImgView)
            }
        }
    }
}