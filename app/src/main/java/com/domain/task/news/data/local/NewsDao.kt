package com.domain.task.news.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.domain.task.news.data.models.BookMarks
import com.domain.task.news.data.models.News


@Dao
interface NewsDao {


    @Query(value = "SELECT news.*,bookmarks.is_bookmark FROM news LEFT JOIN bookmarks ON bookmarks.news_id=news.id")
    fun getNews(): LiveData<List<News>>

    @Query(value = "SELECT news.*,bookmarks.is_bookmark FROM news LEFT JOIN bookmarks ON bookmarks.news_id=news.id")
    fun getNewsAsList(): List<News>

    @Query(value = "SELECT news.*,bookmarks.is_bookmark FROM news INNER JOIN bookmarks ON bookmarks.news_id=news.id AND bookmarks.is_bookmark=1")
    fun getBookMarks(): LiveData<List<News>>

    @Query("SELECT news.*,bookmarks.is_bookmark FROM news LEFT JOIN bookmarks ON bookmarks.news_id=news.id where id=:id")
    fun getNewsById(id: Long): LiveData<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<News>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: News)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoBookMark(favorite: BookMarks)

}