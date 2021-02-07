package com.domain.task.core.api

import com.domain.task.core.connection.ConnectionManager
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


/**
 * A {@see RequestInterceptor} that adds an auth token to requests
 */
class AppInterceptor(private val accessToken: String,private val cm:ConnectionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api-key", accessToken)
            .build()


        val requestBuilder: Request.Builder = original.newBuilder().url(url)


        val request = if (cm.isNetworkAvailable())
            requestBuilder.header("Cache-Control", "public, max-age=" + 5).build()
        else
            requestBuilder.header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
        // Request customization: add request headers

        return chain.proceed(request)
    }

}
