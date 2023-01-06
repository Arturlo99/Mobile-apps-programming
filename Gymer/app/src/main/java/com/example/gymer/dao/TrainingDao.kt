package com.example.gymer.dao

import androidx.room.*
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

    @Insert
    suspend fun insert(training: Training)

    @Update
    suspend fun update(training: Training)

    @Delete
    suspend fun delete(training: Training)

    @Query("DELETE FROM training")
    suspend fun deleteAll()

}