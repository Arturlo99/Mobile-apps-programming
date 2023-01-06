package com.example.a4

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var pwrSiteBtn: Button
    private lateinit var displaySettingsBtn: Button
    private lateinit var takePictureBtn: Button
    private lateinit var photoView: ImageView
    private lateinit var checkStudentBtn: Button
    private lateinit var studentIdEditText: EditText
    private lateinit var studentDataTextView: TextView
    private val pwrSiteLink = "https://pwr.edu.pl/"
    private val cameraRequestCode = 1000
    private val studentRequestCode = 1001


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pwrSiteBtn = findViewById(R.id.pwrSiteBtn)
        displaySettingsBtn = findViewById(R.id.displaySettingsBtn)
        takePictureBtn = findViewById(R.id.takePictureBtn)
        photoView = findViewById(R.id.photoView)
        checkStudentBtn = findViewById(R.id.checkStudentBtn)
        studentIdEditText = findViewById(R.id.studentIdEditText)
        studentDataTextView = findViewById(R.id.studentDataTextView)
    }

    fun openPwrSite(view: View) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(pwrSiteLink)))
    }

    fun openDisplaySettings(view: View) {
        startActivity(Intent(Settings.ACTION_DISPLAY_SETTINGS))
    }

    fun takePhoto(view: View) {
        val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, cameraRequestCode);
    }

    fun openStudentActivity(view: View) {
        val studentId = studentIdEditText.text.toString()
        if (!studentId.equals("")) {
            val intent = Intent(this@MainActivity, StudentActivity::class.java)
            val myDataBundle = Bundle()

            myDataBundle.putString("studentId", studentId)

            intent.putExtras(myDataBundle)
            startActivityForResult(intent, studentRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequestCode && resultCode == RESULT_OK) {
            val resultBundle = data?.extras
            val photo: Bitmap = resultBundle?.get("data") as Bitmap
            photoView.setImageBitmap(photo)
        }
        if (requestCode == studentRequestCode) {
            if (resultCode == RESULT_OK) {
                val resultBundle = data?.extras
                val studentName = resultBundle?.getString("name")
                val studentSurname = resultBundle?.getString("surname")
                val studentGradeProposal = resultBundle?.getString("gradeProposal")
                studentDataTextView.text = "Imię: ".plus(studentName)
                    .plus("\nNazwisko: ").plus(studentSurname)
                    .plus("\nPropozycja oceny: ").plus(studentGradeProposal)
            } else if (resultCode == RESULT_CANCELED) {
                studentDataTextView.text = "Nie znaleziono studenta dla danego numeru indeksu!"
            }
        }
    }
}