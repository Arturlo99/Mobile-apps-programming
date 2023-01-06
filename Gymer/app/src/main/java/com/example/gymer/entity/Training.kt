package com.example.gymer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Training(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val date: LocalDate?,
    val description: String?,
    val photo: Int?,
    val exercises: List<Exercise>?
)

