package com.head.miso.feature.main.ui.map

import com.head.miso.domain.models.message.MessageModel
import com.head.miso.feature.commons.arch.contract.BaseContract
import com.head.miso.feature.main.ui.model.MessageUiModel

interface MapContract {

    interface View : BaseContract.View {

        fun showProgress()

        fun hideProgress()

        fun addMarker(message: MessageModel)

        fun configMarkers()

        fun checkPermissions(): Boolean

        fun requestPermissions()

        fun showErrorPermissions()

        fun checkGps(): Boolean

        fun showErrorGps()

        fun getUserLocation()

        fun refreshMap(location: Pair<Double, Double>)

        fun showErrorLocation()

        fun showDialog()

        fun onErrorPostMessage()
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun setMessages(messageUiModel: MessageUiModel)

        fun onMapReady()

        fun onPermissionsGranted()

        fun onPermissionsRejected()

        fun onLocationFinished(location: Pair<Double, Double>)

        fun onLocationError()

        fun onFloatingButtonClicked()

        fun onSaveMessageClicked(message: Pair<String, String>?)
    }
}
