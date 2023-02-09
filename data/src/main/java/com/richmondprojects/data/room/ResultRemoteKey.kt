package com.richmondprojects.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Remote_key")
data class ResultRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val nextPage: Int?,
    val prePage: Int?
)
