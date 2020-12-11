package com.ydh.photo.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ydh.photo.R
import com.ydh.photo.databinding.ItemPhotosBinding
import com.ydh.photo.domain.PhotoDomain

class PhotoAdapter(
    private val context: Context,
    private val listener: PhotoItemListener
) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    var list = mutableListOf<PhotoDomain>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    interface PhotoItemListener {
        fun onClick(domain: PhotoDomain)
        fun onClickDelete(domain: PhotoDomain)
    }


    class ViewHolder(
        val itemPhotosBinding: ItemPhotosBinding
    ) : RecyclerView.ViewHolder(itemPhotosBinding.root) {

        private var itemBinding: ItemPhotosBinding? = null

        init {
            this.itemBinding = itemPhotosBinding
        }

        companion object {
            @JvmStatic
            @BindingAdapter("photoImg")
            fun loadPhotoImg(view: ImageView, url: String?) {
                var imgUrl =  "https://user-images.githubusercontent.com/24848110/33519396-7e56363c-d79d-11e7-969b-09782f5ccbab.png"

                if (!url.isNullOrEmpty()) {
                    imgUrl = url
                }
                Glide
                    .with(view.context)
                    .load(imgUrl)
                    .into(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemBinding = DataBindingUtil.inflate<ItemPhotosBinding>(
            inflater,
            R.layout.item_photos,
            parent,
            false
        )
        return ViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model by lazy {
            list[position]
        }

        holder.itemPhotosBinding.run {
            photo = model
            ivItemPhotoImg.setOnClickListener {
                listener.onClick(model)
            }
            btDelete.setOnClickListener {
                listener.onClickDelete(model)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}