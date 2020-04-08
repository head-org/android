package com.head.covidapp.di.modules.data

import com.head.covidapp.BuildConfig
import com.head.covidapp.networkdatasource.api.clients.MainApiClient
import org.koin.dsl.module

val networkModule = module {

    single { MainApiClient(BuildConfig.DEBUG, BuildConfig.HOST_NAME) }
}
