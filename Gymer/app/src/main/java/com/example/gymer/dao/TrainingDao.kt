package com.example.gymer.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.gymer.entity.Exercise
import com.example.gymer.entity.Training
import com.example.gymer.entity.TrainingWithExercises

@Dao
interface TrainingDao {

    @Query("SELECT * FROM training")
    fun getAll(): List<Training>

    @Transaction
    @Query("SELECT * FROM training")
    fun getAllWithExcercises(): List<TrainingWithExercises>

    @Query("SELECT * FROM training WHERE id = :id LIMIT 1")
    suspend fun findById(id: Int): Training

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(training: Training): Long

    @Update
    suspend fun update(training: Training)

    @Delete
    suspend fun delete(training: Training)

    @Query("DELETE FROM training")
    suspend fun deleteAll()

    @Transaction
    suspend fun insert(training: Training, exercises: List<Exercise>) {
        val trainingId: Int = insert(training).toInt()

        for (exercise in exercises) {
            exercise.trainingId = trainingId
            insert(exercise)
        }
    }

    @Insert
    suspend fun insert(exercise: Exercise): Long
}