package com.example.gymer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity
data class Training(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    var date: Date?,
    var description: String?,
    var photo: Int?,
)

