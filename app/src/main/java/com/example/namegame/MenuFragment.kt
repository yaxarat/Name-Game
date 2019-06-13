package com.example.namegame


import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
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

        checkPermissions()
        start_button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_gameFragment))
        setting_button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_settingFragment))
    }

    // Start of runtime permission check functions
    private fun checkPermissions() {
        val allPermissionStatus = 1
        val permissions = arrayOf(
            android.Manifest.permission.INTERNET,
            android.Manifest.permission.ACCESS_NETWORK_STATE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )

        for (permission in permissions) {
            if (!hasPermissions(context, permission)) {
                requestPermissions(permissions, allPermissionStatus)
            }
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun hasPermissions(context: Context?, vararg permissions: String): Boolean {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }
}
