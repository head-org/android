package com.head.miso.feature.main.di

import com.head.miso.feature.main.ui.map.MapContract
import com.head.miso.feature.main.ui.map.MapPresenter
import com.head.miso.feature.main.ui.mapper.MapperMessageUiModel
import com.head.miso.feature.main.ui.splash.SplashContract
import com.head.miso.feature.main.ui.splash.SplashPresenter
import org.koin.dsl.module

val mainModule = module {

    // Presenters
    factory<SplashContract.Presenter> { SplashPresenter(get(), get()) }
    factory<MapContract.Presenter> { MapPresenter(get()) }

    // Mappers
    single { MapperMessageUiModel() }
}
