package com.example.namegame

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_menu.*


class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        start_button.setOnClickListener {
            if (isOnline() ) {
                activity?.supportFragmentManager?.beginTransaction()?.addToBackStack("")?.replace(R.id.host_fragment, GameFragment())?.commit()
            } else {
                Toast.makeText(activity, R.string.error_menu_no_network, Toast.LENGTH_LONG).show()
            }
        }
        learn_button.setOnClickListener {
            if (isOnline()) {
                activity?.supportFragmentManager?.beginTransaction()?.addToBackStack("")?.replace(R.id.host_fragment, GameFragment())?.commit()
            } else {
                Toast.makeText(activity, R.string.error_menu_no_network, Toast.LENGTH_LONG).show()
            }
        }
        setting_button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_settingFragment))
    }

    private fun isOnline(): Boolean {
        val connectivityManager = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        return network != null
    }
}
