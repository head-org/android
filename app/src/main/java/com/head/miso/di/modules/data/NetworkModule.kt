package com.head.miso.di.modules.data

import com.head.miso.BuildConfig
import com.head.miso.networkdatasource.api.clients.MainApiClient
import org.koin.dsl.module

val networkModule = module {

    // Api Clients
    single { MainApiClient(BuildConfig.DEBUG, BuildConfig.HOST_NAME, BuildConfig.HEADER) }
}
