package com.example.namegame.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.namegame.MainApp
import com.example.namegame.R
import com.example.namegame.view.adapter.LearnViewAdapter
import com.example.namegame.view.viewmodel.LearnViewModel
import com.example.namegame.view.viewmodel.ViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_learn.*
import javax.inject.Inject

@SuppressLint("CheckResult")
class LearnFragment: Fragment() {
    private lateinit var viewModel: LearnViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        MainApp.application.appComponent.inject(this)
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

    private fun updateUI() {
        viewModel.getAllProfiles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {profileList -> recyclerViewList.adapter = LearnViewAdapter(profileList)},
                {error -> Log.e("tag", "$error")}
            )
    }
}
