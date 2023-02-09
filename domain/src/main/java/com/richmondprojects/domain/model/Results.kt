package com.richmondprojects.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters_table")
data class Results(
    val birth_year: String?="",
    val created: String?="",
    val edited: String?="",
    val eye_color: String?="",
    val gender: String?="",
    val hair_color: String?="",
    val height: String?="",
    val homeworld: String?="",
    val mass: String?="",
    @PrimaryKey(autoGenerate = false)
    val name: String="",
    val skin_color: String?="",
    val url: String?="",
)
