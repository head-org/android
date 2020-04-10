package com.head.covidapp.feature.main.di

import com.head.covidapp.feature.main.ui.map.MapContract
import com.head.covidapp.feature.main.ui.map.MapPresenter
import com.head.covidapp.main.ui.splash.SplashContract
import com.head.covidapp.main.ui.splash.SplashPresenter
import org.koin.dsl.module

val mainModule = module {

    // Presenters
    factory<SplashContract.Presenter> { SplashPresenter(get()) }
    factory<MapContract.Presenter> { MapPresenter() }
}
