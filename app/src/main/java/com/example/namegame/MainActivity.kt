package com.example.namegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.namegame.utility.FragmentTransaction
import com.example.namegame.utility.Permission

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions()
        FragmentTransaction.beginTransaction(this, R.id.host_fragment, MenuFragment())
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
}
