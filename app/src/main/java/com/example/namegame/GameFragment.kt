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
    private var correctProfile = 0

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
        updateUI()
        for (n in 0 until 6) {
            imageViews[n].setOnClickListener{checkAnswer(n)}
        }
    }

    private fun updateUI() = launch {
        textViewScore.text = resources.getString(R.string.game_score, viewModel.score, viewModel.attempt)

        viewModel.profiles.await().observe(this@GameFragment, Observer {
            if (it.isEmpty() || it.size < 6) {
                return@Observer
            }
            progressBar.visibility = View.GONE
            correctProfile = viewModel.answerIndex
            val name: String = it[correctProfile].firstName + " " + it[correctProfile].lastName
            textViewName.text = resources.getString(R.string.game_profile_name, name)

            for (n in 0 until 6) {
                imageViews[n].isClickable = viewModel.clickable[n]!!
                updateHeadshot(imageViews[n].isClickable, n, "https:" + it[n].headshot.url)
            }
        })
    }

    private fun checkAnswer(choice: Int) {
        viewModel.clickable[choice] = false
        imageViews[choice].isClickable = false
        viewModel.attempt++

        if (choice == correctProfile) {
            viewModel.score++
            getNewProfiles()
        } else {
            updateHeadshot(false, choice, "none")
        }
        textViewScore.text = resources.getString(R.string.game_score, viewModel.score, viewModel.attempt)
    }

    private fun updateHeadshot(correct: Boolean, index: Int, src: String) {
        Media.loadImageFromSource(if (correct) src else R.drawable.ic_close, imageViews[index], this@GameFragment.requireContext())
    }

    private fun getNewProfiles() = launch {
        viewModel.newRound()
        updateUI()
    }
}
