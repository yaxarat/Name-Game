package com.example.namegame.adapter.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.namegame.data.entity.Profile
import com.example.namegame.utility.Media
import kotlinx.android.synthetic.main.learn_list_layout.view.*

class ProfileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = this.itemView.imageViewHeadshot
    private val textViewName: TextView = this.itemView.textViewName
    private val textViewJobTitle: TextView = this.itemView.textViewPosition

    fun setProfile(profile: Profile) {
        textViewName.text = profile.firstName + " " + profile.lastName
        textViewJobTitle.text = profile.jobTitle
        val pictureUrl = "https:" + profile.headshot.url

        Media.loadImageFromSource(pictureUrl, imageView, imageView.context)
    }
}