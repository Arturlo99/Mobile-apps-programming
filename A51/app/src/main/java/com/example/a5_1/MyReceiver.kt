package com.example.a5_1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(Intent.ACTION_BATTERY_LOW))
            Toast.makeText(context, "Niski poziom naładowania baterii", Toast.LENGTH_SHORT).show()

        if (intent.action.equals(Intent.ACTION_BATTERY_OKAY))
            Toast.makeText(context, "Bateria jest Ok", Toast.LENGTH_SHORT).show()

        if (intent.action.equals(Intent.ACTION_POWER_CONNECTED))
            Toast.makeText(context, "Podłączono ładowanie", Toast.LENGTH_SHORT).show()

        if (intent.action.equals(Intent.ACTION_POWER_DISCONNECTED))
            Toast.makeText(context, "Odłączono ładowanie", Toast.LENGTH_SHORT).show()
    }
}