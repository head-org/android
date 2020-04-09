package com.head.covidapp.main.di

import com.head.covidapp.main.ui.splash.SplashContract
import com.head.covidapp.main.ui.splash.SplashPresenter
import org.koin.dsl.module

val mainModule = module {

    // Presenters
    factory<SplashContract.Presenter> { SplashPresenter(get()) }
}
