package com.example.namegame

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermissions()

        supportFragmentManager.beginTransaction().replace(R.id.host_fragment, MenuFragment()).commit()
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
            if (!hasPermissions(this, permission)) {
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
