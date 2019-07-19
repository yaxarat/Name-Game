package com.example.namegame.view

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import com.example.namegame.R
import com.example.namegame.database.shared_preference.Preference
import kotlinx.android.synthetic.main.fragment_setting.*
import javax.inject.Inject

class SettingFragment @Inject constructor(private val app: Application) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        switchTheme.isChecked = Preference(app).getTheme()

        switchTheme.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            if (isChecked) {
                enableDarkTheme(true)
            } else {
                enableDarkTheme(false)
            }
        }
    }

    private fun enableDarkTheme(darkTheme: Boolean) {
        Preference(app).setTheme(darkTheme)
        activity?.recreate()
    }
}
