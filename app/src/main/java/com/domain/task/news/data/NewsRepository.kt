package com.domain.task.news.data


import androidx.lifecycle.map
import com.domain.task.core.data.Result
import com.domain.task.core.data.resultLiveData
import com.domain.task.news.data.local.NewsDao
import com.domain.task.news.data.models.BookMarks
import com.domain.task.news.data.remote.NewsRemoteDataSource
import java.util.zip.CRC32
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val news: NewsDao, private val remoteSource: NewsRemoteDataSource
) {

    suspend fun addToBookMark(newId: Long, isBookMarks:Boolean) {
        news.insertIntoBookMark(BookMarks(newId, isBookMarks))
    }


    fun getNewsById(newId: Long) = news.getNewsById(newId).map { Result.success(it) }

    val bookMarks=news.getBookMarks().map { Result.success(it) }

    fun getTopNews() = resultLiveData(
        databaseQuery = {
            news.getNews()
        },
        networkCall = {
            remoteSource.fetchNews()
        },
        saveCallResult = {
            //generate new id using checksum algorithm
            val crc32 = CRC32()
            val listOfNews=it.results;
            listOfNews.forEach { row ->
                crc32.reset()
                crc32.update(row.toString().toByteArray())
                row.id = crc32.value
            }
            news.insertAll(listOfNews)

        }
    )


    companion object {
        val TAG = "NewsRepository"
    }


}

