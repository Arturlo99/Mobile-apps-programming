package com.example.a1

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var context: Context? = null
    private val duration = Toast.LENGTH_SHORT

    private var btnExit: Button? = null
    private var myScreen: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnExit = findViewById<View>(R.id.button1) as Button
        myScreen = findViewById<View>(R.id.myScreen1) as LinearLayout

        btnExit!!.setOnClickListener { finish() }

        context = applicationContext

        Toast.makeText(context, "onCreate", duration).show()
    }

    override fun onStop() {
        super.onStop()
        Log.i("LIFECYCLE", "Called onStop")
        Toast.makeText(context, "onStop", duration).show()
    }

    override fun onStart() {
        super.onStart()
        Log.i("LIFECYCLE", "Called onStart")
        Toast.makeText(context, "onStart", duration).show()
    }

    override fun onResume() {
        super.onResume()
        Log.i("LIFECYCLE", "Called onResume")
        Toast.makeText(context, "onResume", duration).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("LIFECYCLE", "Called onSaveInstanceState")
        Toast.makeText(context, "onSaveInstanceState", duration).show()
    }

    override fun onPause() {
        super.onPause()
        Log.i("LIFECYCLE", "Called onPause")
        Toast.makeText(context, "onPause", duration).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LIFECYCLE", "Called onDestroy")
        Toast.makeText(context, "onDestroy", duration).show()
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LIFECYCLE", "Called onRestart")
        Toast.makeText(context, "onRestart", duration).show()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("LIFECYCLE", "Called onRestoreInstanceState")
        Toast.makeText(context, "onRestoreInstanceState", duration).show()
    }
}