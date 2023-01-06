package com.example.gymer.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Training::class,
        parentColumns = ["id"],
        childColumns = ["trainingId"],
        onDelete = CASCADE
    )]
)
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val trainingId: Int,
    val bodyPart: String?,
    val series: Int?,
    val repetitions: Int?,
    val weight: Int?
)
