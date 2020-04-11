package com.head.covidapp.feature.main.ui.map

import com.head.covidapp.arch.contract.BaseContract
import com.head.covidapp.feature.main.ui.model.MessageUiModel

interface MapContract {

    interface View : BaseContract.View {

        fun showProgress()

        fun hideProgress()

        fun disableIndicatorsButtons()

        fun addMarkers(messageUiModel: MessageUiModel)

        fun setUpInfoMarkers()

        fun checkPermissions(): Boolean

        fun requestPermissions()

        fun showErrorPermissions()

        fun checkGps(): Boolean

        fun showErrorGps()

        fun getUserLocation()

        fun refreshMap(location: Pair<Double, Double>)

        fun showErrorLocation()
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun setMessages(messageUiModel: MessageUiModel)

        fun onMapReady()

        fun onPermissionsGranted()

        fun onPermissionsRejected()

        fun onLocationFinished(location: Pair<Double, Double>)

        fun onLocationError()
    }
}
