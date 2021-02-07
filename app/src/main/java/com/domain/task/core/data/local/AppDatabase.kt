package com.domain.task.core.data.local

import com.domain.task.news.data.local.NewsDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.domain.task.Constant
import com.domain.task.core.data.Converters
import com.domain.task.news.data.models.BookMarks
import com.domain.task.news.data.models.News


@Database(entities = [News::class, BookMarks::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao


    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, Constant.DATABASE_NAME)
                .build()
        }
    }
}
