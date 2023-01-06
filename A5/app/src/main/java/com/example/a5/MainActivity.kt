package com.example.a5

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private var permission by Delegates.notNull<Int>()
    private val permissionRequestCode = 100
    private val myReceiver = MyReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        requestPermissions()
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(Intent.ACTION_BATTERY_OKAY)
        filter.addAction(Intent.ACTION_BATTERY_LOW)
        this.registerReceiver(myReceiver, filter)
    }

    override fun onPause() {
        super.onPause()
        this.unregisterReceiver(myReceiver)
    }

    private fun requestPermissions() {
        permission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), permissionRequestCode)
        }
        permission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), permissionRequestCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            permissionRequestCode -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Uprawnienia nie zostały przyznane!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this, "Przyznano uprawnienia!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}