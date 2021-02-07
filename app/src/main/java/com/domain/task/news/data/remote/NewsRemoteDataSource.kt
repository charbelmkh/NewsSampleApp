package com.domain.task.news.data.remote

import com.domain.task.core.connection.ConnectionManager
import com.domain.task.core.data.remote.BaseDataSource
import javax.inject.Inject


class NewsRemoteDataSource @Inject constructor(connectionManager: ConnectionManager, private val service: NewsService) : BaseDataSource(connectionManager) {

    suspend fun fetchNews() = getResult { service.fetchAllNews() }


}
