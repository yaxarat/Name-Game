package com.example.namegame.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.namegame.R
import com.example.namegame.data.entity.Profile
import kotlinx.android.synthetic.main.learn_list_layout.view.*

class RecyclerViewAdapter (profilesList: List<Profile>): RecyclerView.Adapter<LearnListViewHolder>() {
    private val profiles = profilesList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellRow = layoutInflater.inflate(R.layout.learn_list_layout, parent, false)
        return LearnListViewHolder(cellRow)
    }

    override fun getItemCount(): Int{
        return profiles.size
    }

    override fun onBindViewHolder(holder: LearnListViewHolder, position: Int) {
        val profileName = profiles[position].firstName + " " + profiles[position].lastName
        val jobTitle = profiles[position].jobTitle
        val pictureUrl = "https:" + profiles[position].headshot.url
        val imageView = holder.itemView.imageViewHeadshot

        holder.itemView.textViewName.text = profileName
        holder.itemView.textViewPosition.text = jobTitle
        loadImageFromSource(pictureUrl, imageView, holder.itemView.context)
    }
    private fun loadImageFromSource(source: Any, view: ImageView, context: Context) {
        Log.d("tag", "$source")
        Glide
            .with(context)
            .load(source)
            .circleCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }


}

class LearnListViewHolder(view: View): RecyclerView.ViewHolder(view)