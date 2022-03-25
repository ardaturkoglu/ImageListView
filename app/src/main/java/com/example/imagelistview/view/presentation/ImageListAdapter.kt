package com.example.imagelistview.view.presentation

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
import com.example.imagelistview.view.model.ImageDataModel
import com.example.imagelistview.view.model.ImageList
import com.example.imagelistview.view.util.loadImageWithGlide


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
                loadImageWithGlide(
                    this@ImageListViewHolder.itemView.context,
                    itemImage,
                    loadingTimes
                )
            }
        }
    }
}