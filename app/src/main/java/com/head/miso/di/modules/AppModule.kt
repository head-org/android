package com.head.miso.di.modules

import com.head.miso.di.modules.data.networkModule
import com.head.miso.di.modules.feature.messageModule
import com.head.miso.feature.main.di.mainModule
import org.koin.core.module.Module

private val dataModules = listOf(networkModule)
private val featureModules = listOf(mainModule, messageModule)

val appModule = mutableListOf<Module>().apply {
    addAll(dataModules)
    addAll(featureModules)
}
