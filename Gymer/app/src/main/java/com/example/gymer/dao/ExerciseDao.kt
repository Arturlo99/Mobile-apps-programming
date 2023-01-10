package com.example.gymer.dao

import androidx.room.*
import com.example.gymer.entity.Exercise
import com.example.gymer.entity.Training

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercise")
    fun getAll(): List<Training>

    @Query("SELECT * FROM exercise WHERE id = :id LIMIT 1")
    suspend fun findById(id: Int): Exercise

    @Insert
    suspend fun insert(exercise: Exercise)

    @Update
    suspend fun update(exercise: Exercise)

    @Delete
    suspend fun delete(exercise: Exercise)

    @Query("DELETE FROM exercise")
    suspend fun deleteAll()
}