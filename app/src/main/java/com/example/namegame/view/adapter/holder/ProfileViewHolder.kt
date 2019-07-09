package com.example.namegame.view.adapter.holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.namegame.R
import com.example.namegame.database.entity.Profile
import com.example.namegame.utility.Media
import kotlinx.android.synthetic.main.learn_list_layout.view.*

class ProfileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val context: Context = itemView.context
    private val imageView: ImageView = this.itemView.imageViewHeadshot
    private val textViewName: TextView = this.itemView.textViewName
    private val textViewPosition: TextView = this.itemView.textViewPosition

    fun setProfile(profile: Profile) {
        textViewName.text = context.resources.getString(R.string.profile_name, profile.firstName, profile.lastName)
        textViewPosition.text = profile.jobTitle
        val pictureUrl = "https:" + profile.headshot.url

        Media.loadImageFromSource(pictureUrl, imageView, imageView.context)
    }
}