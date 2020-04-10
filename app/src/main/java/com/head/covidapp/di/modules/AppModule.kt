package com.head.covidapp.di.modules

import com.head.covidapp.di.modules.data.networkModule
import com.head.covidapp.feature.main.di.mainModule
import org.koin.core.module.Module

private val dataModules = listOf(networkModule)
private val featureModules = listOf(mainModule)

val appModule = mutableListOf<Module>().apply {
    addAll(dataModules)
    addAll(featureModules)
}
