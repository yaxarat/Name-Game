package com.example.namegame.utility

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class Media {
    companion object {
        fun loadImageFromSource(source: Any, view: ImageView, context: Context) {
            Glide
                .with(context)
                .load(source)
                .circleCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        }
    }
}