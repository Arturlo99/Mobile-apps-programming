package com.example.a6

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHandler(
    context: Context, name: String?,
    factory: SQLiteDatabase.CursorFactory?, version: Int
) :
    SQLiteOpenHelper(
        context, DATABASE_NAME,
        factory, DATABASE_VERSION
    ) {

    companion object {

        private val DATABASE_VERSION = 2
        private val DATABASE_NAME = "fieldsOfStudyDB.db"
        val TABLE_FIELDS_OF_STUDY = "fields_of_study"

        val COLUMN_ID = "_id"
        val COLUMN_NAME = "name"
        val COLUMN_SPECIALITY = "speciality"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_FIELDS_OF_STUDY_TABLE = ("CREATE TABLE " +
                TABLE_FIELDS_OF_STUDY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME
                + " TEXT NOT NULL, " + COLUMN_SPECIALITY + " TEXT" + ")")
        db.execSQL(CREATE_FIELDS_OF_STUDY_TABLE)
        db.execSQL(
            "INSERT INTO $TABLE_FIELDS_OF_STUDY ($COLUMN_NAME, $COLUMN_SPECIALITY) " +
                    "VALUES (\"Cyberbezpieczeństwo\",\"INS\")"
        )
        db.execSQL(
            "INSERT INTO $TABLE_FIELDS_OF_STUDY ($COLUMN_NAME, $COLUMN_SPECIALITY) " +
                    "VALUES (\"Informatyka Algorytmiczna\",\"IMT\")"
        )
        db.execSQL(
            "INSERT INTO $TABLE_FIELDS_OF_STUDY ($COLUMN_NAME, $COLUMN_SPECIALITY) " +
                    "VALUES (\"Informatyka Techniczna\",\"ISK\")"
        )
        db.execSQL(
            "INSERT INTO $TABLE_FIELDS_OF_STUDY ($COLUMN_NAME, $COLUMN_SPECIALITY) " +
                    "VALUES (\"Sztuczna Inteligencja\",\"ISK\")"
        )
        db.execSQL(
            "INSERT INTO $TABLE_FIELDS_OF_STUDY ($COLUMN_NAME, $COLUMN_SPECIALITY) " +
                    "VALUES (\"Telekomunikacja\",\"ISK\")"
        )
        db.execSQL(
            "INSERT INTO $TABLE_FIELDS_OF_STUDY ($COLUMN_NAME, $COLUMN_SPECIALITY) " +
                    "VALUES (\"Informatyczne Systemy Automatyki\",\"ISK\")"
        )
        db.execSQL(
            "INSERT INTO $TABLE_FIELDS_OF_STUDY ($COLUMN_NAME, $COLUMN_SPECIALITY) " +
                    "VALUES (\"Informatyka Stosowana\",\"ISK\")"
        )
        db.execSQL(
            "INSERT INTO $TABLE_FIELDS_OF_STUDY ($COLUMN_NAME, $COLUMN_SPECIALITY) " +
                    "VALUES (\"Inżynieria Systemów\",\"ISK\")"
        )
        db.execSQL(
            "INSERT INTO $TABLE_FIELDS_OF_STUDY ($COLUMN_NAME, $COLUMN_SPECIALITY) " +
                    "VALUES (\"Inżynieria Systemów\",\"ISK\")"
        )
        db.execSQL(
            "INSERT INTO $TABLE_FIELDS_OF_STUDY ($COLUMN_NAME, $COLUMN_SPECIALITY) " +
                    "VALUES (\"Teleinformatyka\",\"ISK\")"
        )
        db.execSQL(
            "INSERT INTO $TABLE_FIELDS_OF_STUDY ($COLUMN_NAME, $COLUMN_SPECIALITY) " +
                    "VALUES (\"Zaufane Systemy Sztucznej Inteligencji\",\"ISK\")"
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase, oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_FIELDS_OF_STUDY")
        onCreate(db)
    }

    fun addFieldOfStudy(fieldOfStudy: FieldOfStudy) {

        val values = ContentValues()
        values.put(COLUMN_NAME, fieldOfStudy.name)
        values.put(COLUMN_SPECIALITY, fieldOfStudy.speciality)

        val db = this.writableDatabase
        db.insert(TABLE_FIELDS_OF_STUDY, null, values)

        db.close()
    }

    fun findFieldOfStudy(fieldOfStudyName: String): FieldOfStudy? {
        val query =
            "SELECT * FROM $TABLE_FIELDS_OF_STUDY WHERE $COLUMN_NAME =  \"$fieldOfStudyName\""

        val db = this.writableDatabase

        val cursor = db.rawQuery(query, null)

        var fieldOfStudy: FieldOfStudy? = null

        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val id = Integer.parseInt(cursor.getString(0))
            val name = cursor.getString(1)
            val speciality = cursor.getString(2)
            fieldOfStudy = FieldOfStudy(id, name, speciality)
            cursor.close()
        }

        db.close()
        return fieldOfStudy
    }

    fun findAllFieldsOfStudy(): ArrayList<FieldOfStudy> {
        val resultList = ArrayList<FieldOfStudy>()
        val query = "SELECT * FROM $TABLE_FIELDS_OF_STUDY"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val id = Integer.parseInt(cursor.getString(0))
            val name = cursor.getString(1)
            val speciality = cursor.getString(2)
            val fieldOfStudy = FieldOfStudy(id, name, speciality)
            resultList.add(fieldOfStudy)
        }
        cursor.close()
        db.close()
        return resultList
    }

    fun deleteFieldOfStudy(id: Int) {
        val query =
            "DELETE FROM $TABLE_FIELDS_OF_STUDY WHERE $COLUMN_ID = \"$id\""
        val db = this.writableDatabase
        db.execSQL(query)
        db.close()
    }

    fun editFieldOfStudy(fieldOfStudy: FieldOfStudy) {
        val query =
            "UPDATE $TABLE_FIELDS_OF_STUDY SET $COLUMN_NAME = \"${fieldOfStudy.name}\", " +
                    "$COLUMN_SPECIALITY = \"${fieldOfStudy.speciality}\"" +
                    " WHERE $COLUMN_ID = \"${fieldOfStudy.id}\""
        val db = this.writableDatabase
        db.execSQL(query)
        db.close()
    }
}
