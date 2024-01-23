package com.codingurkan.pokeapplication.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.codingurkan.pokeapplication.R

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_launcher_background)
        .into(this)
}