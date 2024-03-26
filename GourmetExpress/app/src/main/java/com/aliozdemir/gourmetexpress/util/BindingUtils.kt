package com.aliozdemir.gourmetexpress.util

import android.widget.ImageView
import com.aliozdemir.gourmetexpress.R
import com.bumptech.glide.Glide

object BindingUtils {
    fun bindImage(imageView: ImageView, imageName: String) {
        val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
        Glide.with(imageView)
            .load(imageUrl)
            .placeholder(R.drawable.ic_baseline_refresh_24px)
            .into(imageView)
    }
}