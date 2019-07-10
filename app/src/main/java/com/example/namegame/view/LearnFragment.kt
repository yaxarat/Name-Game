package com.example.namegame.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.namegame.MainApp
import com.example.namegame.R
import com.example.namegame.ScopedFragment
import com.example.namegame.view.adapter.LearnViewAdapter
import com.example.namegame.view.viewmodel.LearnViewModel
import com.example.namegame.view.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_learn.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class LearnFragment : ScopedFragment() {
    private lateinit var viewModel: LearnViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        MainApp.app.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LearnViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_learn, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewList.adapter = LearnViewAdapter(emptyList()) // set an empty adapter first to avoid "No adapter attached" runtime error
        updateUI()
    }

    private fun updateUI() = launch {
        viewModel.profiles.await().observe(this@LearnFragment, Observer {
            recyclerViewList.adapter = LearnViewAdapter(it)
        })
    }
}
