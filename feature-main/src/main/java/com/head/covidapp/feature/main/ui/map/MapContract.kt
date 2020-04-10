package com.head.covidapp.feature.main.ui.map

import com.head.covidapp.arch.contract.BaseContract

interface MapContract {

    interface View : BaseContract.View {

        fun checkPermissions(): Boolean

        fun requestPermissions()

        fun addMarkers()
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun onMapReady()

    }
}
