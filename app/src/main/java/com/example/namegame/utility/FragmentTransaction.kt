package com.example.namegame.utility

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class FragmentTransaction {
    companion object {
        fun beginTransaction(activity: FragmentActivity?, fromFragment: Int, toFragment: Fragment) {
            activity?.supportFragmentManager?.beginTransaction()?.replace(fromFragment, toFragment)?.addToBackStack(null)?.commit()
        }
    }
}