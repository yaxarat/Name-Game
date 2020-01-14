package com.example.namegame.view

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.namegame.MainApp
import com.example.namegame.R
import com.example.namegame.database.shared_preference.Preference
import com.example.namegame.utility.FragmentTransaction
import com.example.namegame.utility.Permission
import javax.inject.Inject

class MainActivity : AppCompatActivity(){
    @Inject lateinit var app: Application

    override fun onCreate(savedInstanceState: Bundle?) {
        MainApp.application.appComponent.inject(this)
        if (Preference(app).getTheme()) {setTheme(R.style.DarkTheme)}
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions()

        //  using the same Fragment rather than recreating a new one
        if (savedInstanceState == null) {
            FragmentTransaction.beginTransaction(this, R.id.host_fragment, MenuFragment())
        }
    }

    private fun requestPermissions() {
        val permissions = arrayOf(
            android.Manifest.permission.INTERNET,
            android.Manifest.permission.ACCESS_NETWORK_STATE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )

        for (permission in permissions) {
            if (!Permission.checkPermissions(this, permission)) {
                requestPermissions(permissions, 1)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
