package com.example.namegame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.example.namegame.data.shared_preference.Preference
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        switchTheme.isChecked = Preference(this.requireContext()).getTheme()

        switchTheme.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            if (isChecked) {
                enableDarkTheme(true)
            } else {
                enableDarkTheme(false)
            }
        }
    }

    private fun enableDarkTheme(darkTheme: Boolean) {
        Preference(this.requireContext()).setTheme(darkTheme)
        activity?.recreate()
    }
}
