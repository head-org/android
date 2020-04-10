package com.head.covidapp.feature.main.di

import com.head.covidapp.feature.main.ui.map.MapContract
import com.head.covidapp.feature.main.ui.map.MapPresenter
import org.koin.dsl.module

val mainModule = module {

    // Presenters
    factory<MapContract.Presenter> { MapPresenter() }
}
