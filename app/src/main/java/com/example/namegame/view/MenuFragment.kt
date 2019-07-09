package com.example.namegame.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.namegame.R
import com.example.namegame.utility.FragmentTransaction
import com.example.namegame.utility.Network
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
