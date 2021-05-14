package com.example.ilovecats.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso



    @BindingAdapter("bind:imageUrl")
    fun loadImage(imageView: ImageView,url: String){
        if (!url.isNullOrEmpty()){
            Picasso.get()
                .load(url)

                .into(imageView)
        }
    }
    @BindingAdapter("bind:imageUrlFull")
    fun loadFullImage(imageView: ImageView,url: String){
        if (!url.isNullOrEmpty()){
            Picasso.get()
                .load(url)
                .into(imageView)
        }
    }
