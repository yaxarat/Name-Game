package com.example.namegame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.namegame.utility.Media
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
    private lateinit var imageViews: Array<ImageView>
    private var pickedIndex = mutableListOf<Int>()
    private var answerProfile = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)
        imageViews = arrayOf(
            imageViewHeadshot1,
            imageViewHeadshot2,
            imageViewHeadshot3,
            imageViewHeadshot4,
            imageViewHeadshot5,
            imageViewHeadshot6
        )

        bindUI()

        imageViews[0].setOnClickListener { checkAnswer(0) }
        imageViews[1].setOnClickListener { checkAnswer(1) }
        imageViews[2].setOnClickListener { checkAnswer(2) }
        imageViews[3].setOnClickListener { checkAnswer(3) }
        imageViews[4].setOnClickListener { checkAnswer(4) }
        imageViews[5].setOnClickListener { checkAnswer(5) }
    }

    private fun bindUI() = launch {
        val profiles = viewModel.profiles.await()
        answerProfile = viewModel.answerIndex

        profiles.observe(this@GameFragment, Observer {
            if (it.isEmpty() || it.size < 6) {
                return@Observer
            }

            val name: String = it[answerProfile].firstName + " " + it[answerProfile].lastName
            textViewName.text = resources.getString(R.string.game_profile_name, name)

            for (n in 0 until it.size) {
                Media.loadImageFromSource("https:" + it[n].headshot.url, imageViews[n], this@GameFragment.requireContext())
            }
        })
    }

    private fun checkAnswer(choice: Int) {
        if (!pickedIndex.contains(choice)) {
            pickedIndex.add(choice)
            viewModel.attempt++
        }

        if (choice == answerProfile) {
            getNewProfiles()
            viewModel.score++
        }

        Media.loadImageFromSource(if (choice == answerProfile) R.drawable.ic_check else R.drawable.ic_close, imageViews[choice], this@GameFragment.requireContext())
        textViewScore.text = resources.getString(R.string.game_score, viewModel.score, viewModel.attempt)
    }

    private fun getNewProfiles() = launch {
        pickedIndex.clear()
        viewModel.getNewProfiles()
        viewModel.getNewAnswerIndex()
        bindUI()
    }
}
