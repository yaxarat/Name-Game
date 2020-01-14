package com.example.namegame.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.namegame.MainApp
import com.example.namegame.R
import com.example.namegame.database.repository.ProfileRepository
import com.example.namegame.utility.FragmentTransaction
import com.example.namegame.utility.Network
import kotlinx.android.synthetic.main.fragment_menu.*
import javax.inject.Inject

class MenuFragment: Fragment() {
    @Inject lateinit var profileRepository: ProfileRepository

    override fun onAttach(context: Context) {
        MainApp.application.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileRepository.fetchProfiles()

        start_button.setOnClickListener {
            beginTransactionTo(GameFragment())
        }
        learn_button.setOnClickListener {
            beginTransactionTo(LearnFragment())
        }
        setting_button.setOnClickListener {
            FragmentTransaction.beginTransactionWithBackStack(activity, R.id.host_fragment, SettingFragment())
        }
    }

    private fun beginTransactionTo(destinationFragment: Fragment) {
        if (Network.isOnline(requireContext())) {
            FragmentTransaction.beginTransactionWithBackStack(activity, R.id.host_fragment, destinationFragment)
        } else {
            Toast.makeText(activity, R.string.error_menu_no_network, Toast.LENGTH_LONG).show()
        }
    }
}
