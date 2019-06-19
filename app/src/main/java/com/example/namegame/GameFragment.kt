package com.example.namegame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.namegame.viewmodel.GameViewModel
import com.example.namegame.viewmodel.GameViewModelFactory
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance



class GameFragment : ScopedFragment(), KodeinAware {
    override val kodein by kodein()
    private val viewModelFactory: GameViewModelFactory by instance()

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch {
        val profiles = viewModel.profile.await()
        val rnds = viewModel.rnds
        val imageViews = arrayOf<ImageView>(
            imageViewHeadshot1,
            imageViewHeadshot2,
            imageViewHeadshot3,
            imageViewHeadshot4,
            imageViewHeadshot5,
            imageViewHeadshot6
        )

        profiles.observe(this@GameFragment, Observer {
            if (it.isEmpty() || it.size < 6) {return@Observer}

            val name: String = it[rnds].firstName + " " + it[rnds].lastName
            textViewName.text = resources.getString(R.string.game_profile_name, name)

            for (n in 0 until it.size) {
                loadImageFromUrl("https:" + it[n].headshot.url, imageViews[n])
            }
        })
    }

    private fun loadImageFromUrl(url: String, view: ImageView) {
        Glide
            .with(this@GameFragment)
            .load(url).placeholder(R.drawable.wt_logo)
            .circleCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}
