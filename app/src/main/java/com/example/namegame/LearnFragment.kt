package com.example.namegame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.namegame.adapter.LearnViewAdapter
import com.example.namegame.viewmodel.LearnViewModel
import com.example.namegame.viewmodel.LearnViewModelFactory
import kotlinx.android.synthetic.main.fragment_learn.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class LearnFragment : ScopedFragment(), KodeinAware {
    override val kodein by kodein()
    private lateinit var viewModel: LearnViewModel
    private val viewModelFactory: LearnViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_learn, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LearnViewModel::class.java)
        recyclerViewList.adapter = LearnViewAdapter(emptyList()) // set an empty adapter first to avoid "No adapter attached" error
        updateUI()
    }

    private fun updateUI() = launch {
        viewModel.profiles.await().observe(this@LearnFragment, Observer {
            recyclerViewList.adapter = LearnViewAdapter(it)
        })
    }
}
