package com.domain.task

class Constant {
    companion object {
        const val subdomain = BuildConfig.SUBDOMAIN
        const val protocol = BuildConfig.PROTOCOL
        const val hostname = BuildConfig.HOST_NAME
        const val port = BuildConfig.PORT
        const val certificate = BuildConfig.CERTIFICATE
        const val domain = "$protocol://$subdomain$hostname:$port/"
        const val DATABASE_NAME = "app-db"

    }
}