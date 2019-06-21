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
    private var randomInt = 0
    private lateinit var imageViews: Array<ImageView>
    private var pickedIndex = mutableListOf<Int>()

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

        imageViewHeadshot1.setOnClickListener { checkAnswer(0) }
        imageViewHeadshot2.setOnClickListener { checkAnswer(1) }
        imageViewHeadshot3.setOnClickListener { checkAnswer(2) }
        imageViewHeadshot4.setOnClickListener { checkAnswer(3) }
        imageViewHeadshot5.setOnClickListener { checkAnswer(4) }
        imageViewHeadshot6.setOnClickListener { checkAnswer(5) }
    }

    private fun bindUI() = launch {
        val profiles = viewModel.profiles.await()
        randomInt = viewModel.answerIndex
        imageViews = arrayOf(
            imageViewHeadshot1,
            imageViewHeadshot2,
            imageViewHeadshot3,
            imageViewHeadshot4,
            imageViewHeadshot5,
            imageViewHeadshot6
        )

        profiles.observe(this@GameFragment, Observer {
            if (it.isEmpty() || it.size < 6) { return@Observer }

            val name: String = it[randomInt].firstName + " " + it[randomInt].lastName
            textViewName.text = resources.getString(R.string.game_profile_name, name)

            for (n in 0 until it.size) {
                loadImageFromSource("https:" + it[n].headshot.url, imageViews[n])
            }
        })
    }

    private fun getNewProfiles() = launch {
        pickedIndex.clear()
        viewModel.getNewProfiles()
        viewModel.getNewAnswerIndex()
        bindUI()
    }

    private fun loadImageFromSource(source: Any, view: ImageView) {
        Glide
            .with(this@GameFragment)
            .load(source)
            .circleCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }

    private fun checkAnswer(choice: Int) {
        if (!pickedIndex.contains(choice)) {
            pickedIndex.add(choice)
            viewModel.attempt ++
        }

        if (choice == randomInt) {
            revealChoice(choice, true)
            getNewProfiles()
            viewModel.score ++
            textViewScore.text = resources.getString(R.string.game_score, viewModel.score, viewModel.attempt)
        } else {
            revealChoice(choice, false)
            textViewScore.text = resources.getString(R.string.game_score, viewModel.score, viewModel.attempt)
        }
    }

    private fun revealChoice(choice: Int, isCorrect: Boolean) {
        if (isCorrect) {
            loadImageFromSource(R.drawable.ic_check, imageViews[choice])
        } else {
            loadImageFromSource(R.drawable.ic_close, imageViews[choice])
        }
    }
}
