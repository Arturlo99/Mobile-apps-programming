package com.example.a4

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class StudentActivity : AppCompatActivity() {
    private lateinit var studentIdTextView: TextView
    private lateinit var myBundle: Bundle
    private lateinit var myCallerIntent: Intent
    private lateinit var studentId: String
    private val myId = "248854"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        studentIdTextView = findViewById(R.id.studentIdtextView)
        myCallerIntent = intent
        myBundle = myCallerIntent.extras!!
        studentId = myBundle.getString("studentId").toString()
        studentIdTextView.text = studentId;
    }

    fun returnStudentData(view: View) {
        if (studentId.equals(myId)) {
            myBundle.putString("name", "Artur")
            myBundle.putString("surname", "Sołtys")
            myBundle.putString("gradeProposal", "5.0")
            myCallerIntent.putExtras(myBundle)
            setResult(Activity.RESULT_OK, myCallerIntent)
        } else {
            setResult(Activity.RESULT_CANCELED, myCallerIntent)
        }
        finish()
    }
}