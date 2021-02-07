package com.domain.task.news.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.domain.task.core.data.Result
import com.domain.task.news.data.NewsRepository
import com.domain.task.news.data.models.News
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val repo: NewsRepository) : ViewModel() {




    val newsLiveData = MediatorLiveData<Result<List<News>>>()
    val bookMarks = MediatorLiveData<Result<List<News>>>()

    init {
        Log.d(TAG, "init called")
        fetchTopNews()           // ViewModel is created only once during Activity/Fragment lifetime
        fetchBookMark()
    }

    fun loadNewsDetails(newsId: Long): LiveData<Result<News>> {
        return repo.getNewsById(newsId)
    }

    private fun fetchBookMark() {
        bookMarks.removeSource(repo.bookMarks)
        bookMarks.addSource(repo.bookMarks, Observer {
            Log.d(TAG, "fetchBookMark  addSource")
            bookMarks.postValue(it)
        })

    }

    fun fetchTopNews() {
        val source = repo.getTopNews()
        Log.d(TAG, "fetchTopNews $source")
        newsLiveData.removeSource(source)
        newsLiveData.addSource(source, Observer {
            Log.d(TAG, "fetchTopNews  addSource")
            newsLiveData.postValue(it)
        })
    }

    fun addToBookMark(newId: Long, isBookMark: Boolean) {
        viewModelScope.launch {
            repo.addToBookMark(newId, isBookMark)
        }
    }

    companion object {
        private const val TAG = "NewsViewModel"
    }


}