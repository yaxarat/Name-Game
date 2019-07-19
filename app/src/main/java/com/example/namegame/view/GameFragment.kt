package com.example.namegame.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.namegame.MainApp
import com.example.namegame.R
import com.example.namegame.database.entity.Profile
import com.example.namegame.utility.Media
import com.example.namegame.view.viewmodel.GameViewModel
import com.example.namegame.view.viewmodel.ViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_game.*
import javax.inject.Inject

@SuppressLint("CheckResult")
class GameFragment: Fragment() {
    private lateinit var viewModel: GameViewModel
    private lateinit var imageViews: Array<ImageView>
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private var correctProfile = 0

    override fun onAttach(context: Context) {
        MainApp.application.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imageViews = arrayOf(
            imageViewHeadshot1,
            imageViewHeadshot2,
            imageViewHeadshot3,
            imageViewHeadshot4,
            imageViewHeadshot5,
            imageViewHeadshot6
        )
        getNewProfiles()
        for (n in 0 until 6) {
            imageViews[n].setOnClickListener{checkAnswer(n)}
        }
    }

    private fun getNewProfiles() {
        viewModel.newRound()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {profiles -> updateUI(profiles) },
                {error -> Log.e("tag", "$error")}
            )
    }

    private fun updateUI(profileList: List<Profile>){

        textViewScore.text = resources.getString(R.string.game_score, viewModel.score, viewModel.attempt)
        progressBar.visibility = View.GONE
        correctProfile = viewModel.answerIndex
        val name: String = profileList[correctProfile].firstName + " " + profileList[correctProfile].lastName

        for (n in 0 until 6) {
            imageViews[n].isClickable = viewModel.clickable[n]
            updateHeadshot(imageViews[n].isClickable, n, "https:" + profileList[n].headshot.url)
        }
        textViewName.text = resources.getString(R.string.game_profile_name, name)
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
}