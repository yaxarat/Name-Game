package com.example.namegame


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namegame.adapter.RecyclerViewAdapter
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learn, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LearnViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch {
        val profiles = viewModel.profiles.await()

        profiles.observe(this@LearnFragment, Observer {
            recyclerViewList.layoutManager = LinearLayoutManager(activity)
            recyclerViewList.adapter = RecyclerViewAdapter(it)
        })
    }


}
