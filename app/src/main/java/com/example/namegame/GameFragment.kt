package com.example.namegame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.namegame.data.service.*
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

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
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        val retrofit = ProfileApi(ConnectivityInterceptorImpl(this.context!!))
        val profileDataSource = ProfileDataSourceImpl(retrofit)

        profileDataSource.downloadedProfiles.observe(this, Observer {
            for (profile in it) {
                var content = ""
                content += "First Name: " + profile.firstName + "\n"
                content += "Last Name: " + profile.lastName + "\n\n"
                textViewTest.append(content)
            }
        })

        GlobalScope.launch(Dispatchers.Main) {
            profileDataSource.fetchProfiles()
        }
    }
}
