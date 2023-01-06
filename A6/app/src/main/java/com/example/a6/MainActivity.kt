package com.example.a6

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.a6.adapter.ItemAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var dbHandler: MyDBHandler
    private lateinit var recyclerView: RecyclerView
    private lateinit var updateButton: Button
    private lateinit var addButton: Button
    private lateinit var deleteButton: Button
    private lateinit var editTextName: EditText
    private lateinit var editTextSpeciality: EditText
    private lateinit var dataSet: ArrayList<FieldOfStudy>
    private lateinit var idValueTextView: TextView
    private var foundFieldOfStudy: FieldOfStudy? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHandler = MyDBHandler(this, null, null, 1)
        dataSet = dbHandler.findAllFieldsOfStudy()
        recyclerView = findViewById(R.id.recyclerView)
        updateButton = findViewById(R.id.updateButton)
        addButton = findViewById(R.id.addButton)
        deleteButton = findViewById(R.id.deleteButton)
        editTextName = findViewById(R.id.editTextName)
        idValueTextView = findViewById(R.id.idValueTextView)
        editTextSpeciality = findViewById(R.id.editTextSpeciality)
        recyclerView.adapter = ItemAdapter(this, dataSet)
        updateButton.isActivated = false
        recyclerView.setHasFixedSize(true)
    }

    fun addButtonHandler(view: View) {
        val name = editTextName.text.toString().trim()
        val speciality = editTextSpeciality.text.toString().trim()
        if (name.isNotEmpty() && speciality.isNotEmpty()) {
            val newFieldOfStudy = FieldOfStudy(name, speciality)
            dbHandler.addFieldOfStudy(newFieldOfStudy)
            dataSet.add(newFieldOfStudy)
            dataSet.find { e -> e.name == " dupa" }
            recyclerView.adapter?.notifyItemInserted(dataSet.lastIndex)
            Toast.makeText(this, "Pomyślnie dodano nowy kierunek!", Toast.LENGTH_SHORT).show()
            editTextName.setText("")
            editTextSpeciality.setText("")
            idValueTextView.text = ""
        } else {
            Toast.makeText(this, "Wprowadź dane do wszystkich pól!", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteButtonHandler(view: View) {
        val name = editTextName.text.toString().trim()
        val entityToBeDeleted: FieldOfStudy?
        if (name.isNotEmpty()) {
            entityToBeDeleted = dbHandler.findFieldOfStudy(name)
            if (entityToBeDeleted != null) {
                val position = dataSet.indexOf(entityToBeDeleted)
                dbHandler.deleteFieldOfStudy(entityToBeDeleted.id)
                dataSet.remove(entityToBeDeleted)
                recyclerView.adapter?.notifyItemRemoved(position)
                Toast.makeText(
                    this,
                    "Pomyślnie usunięto kierunek o danej nazwie!",
                    Toast.LENGTH_SHORT
                ).show()
                idValueTextView.text = ""
                editTextName.setText("")
                editTextSpeciality.setText("")
                updateButton.isActivated = false
            } else {
                Toast.makeText(this, "Nie usunięto kierunku o danej nazwie!", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(
                this,
                "Wprowadź nazwę kierunku, który chcesz usunąć!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun updateButtonHandler(view: View) {
        if (foundFieldOfStudy != null) {
            val fieldOfStudy = FieldOfStudy(foundFieldOfStudy!!.id, editTextName.text.toString(),
                editTextSpeciality.text.toString()
            )
            val position = dataSet.indexOf(foundFieldOfStudy)
            dbHandler.editFieldOfStudy(fieldOfStudy)
            dataSet[position] = fieldOfStudy
            recyclerView.adapter?.notifyItemChanged(position)
            editTextName.setText("")
            editTextSpeciality.setText("")
            idValueTextView.text = ""
            Toast.makeText(this, "Zaktualizowano dany kierunek", Toast.LENGTH_SHORT)
                .show()
            updateButton.isActivated = false
        }
    }

    fun searchButtonHandler(view: View) {
        val name = editTextName.text.toString().trim()
        if (name.isNotEmpty()) {
            foundFieldOfStudy = dbHandler.findFieldOfStudy(name)
            if (foundFieldOfStudy != null) {
                idValueTextView.text = foundFieldOfStudy!!.id.toString()
                editTextName.setText(foundFieldOfStudy!!.name)
                editTextSpeciality.setText(foundFieldOfStudy!!.speciality)
                updateButton.isActivated = true
            } else {
                Toast.makeText(this, "Nie znaleziono kierunku o danej nazwie!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}