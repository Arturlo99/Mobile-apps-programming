package com.example.gymer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gymer.dao.TrainingDao
import com.example.gymer.entity.Exercise
import com.example.gymer.entity.Training
import java.time.LocalDate

@Database(entities = [Training::class, Exercise::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trainingDao(): TrainingDao

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        val tempInstance = INSTANCE
        if (tempInstance != null) {
            return tempInstance
        }
        synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).addCallback(roomCallback)
                .build()
            INSTANCE = instance
            return instance
        }
    }

    private val roomCallback = object : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            populateDatabase(INSTANCE!!)
        }
    }

    private fun populateDatabase(db: AppDatabase) {
//        trainingDao.insert(Training(1, LocalDate.now(), "abc", 1, emptyList()))
//        trainingDao.insert(Training(2, LocalDate.now(), "abcd", 1, emptyList()))
//        trainingDao.insert(Training(3, LocalDate.now(), "abcde", 1, emptyList()))
    }
}