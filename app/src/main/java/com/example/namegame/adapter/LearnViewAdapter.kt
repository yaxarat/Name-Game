package com.example.namegame.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.namegame.R
import com.example.namegame.adapter.holder.ProfileViewHolder
import com.example.namegame.data.entity.Profile

class LearnViewAdapter (profilesList: List<Profile>): RecyclerView.Adapter<ViewHolder>() {
    private val profiles = profilesList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellRow = layoutInflater.inflate(R.layout.learn_list_layout, parent, false)
        return ProfileViewHolder(cellRow)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ProfileViewHolder).setProfile(profiles[position])
    }

    override fun getItemCount(): Int{
        return profiles.size
    }
}