package com.head.covidapp.di.modules

import com.head.covidapp.di.modules.data.networkModule
import org.koin.dsl.module

val appModule = module {
    listOf(
        networkModule
    )
}
