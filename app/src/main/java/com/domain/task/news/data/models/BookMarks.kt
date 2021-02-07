package com.domain.task.news.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "bookmarks")
data class BookMarks(
    @PrimaryKey @ColumnInfo(name = "news_id")
    @field:SerializedName("news_id")val news_id: Long = 0,
    @field:SerializedName("is_bookmark") val is_bookmark: Boolean,

)

