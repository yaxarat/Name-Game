package com.example.namegame.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.namegame.R
import com.example.namegame.database.entity.Profile
import com.example.namegame.view.adapter.holder.ProfileViewHolder

class LearnViewAdapter (private val profilesList: List<Profile>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellRow = layoutInflater.inflate(R.layout.learn_list_layout, parent, false)
        return ProfileViewHolder(cellRow)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ProfileViewHolder).setProfile(profilesList[position])
    }

    override fun getItemCount(): Int{
        return profilesList.size
    }
}