package com.example.gymer.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TrainingWithExercises(
    @Embedded val training: Training,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val exercises: List<Exercise>
)
