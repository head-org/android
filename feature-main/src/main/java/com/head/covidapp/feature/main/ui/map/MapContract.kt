package com.head.covidapp.feature.main.ui.map

import com.head.covidapp.arch.contract.BaseContract
import com.head.covidapp.feature.main.ui.model.MessageUiModel

interface MapContract {

    interface View : BaseContract.View {

        fun showProgress()

        fun hideProgress()

        fun checkPermissions(): Boolean

        fun requestPermissions()

        fun disableIndicatorsButtons()

        fun addMarkers(messageUiModel: MessageUiModel)

        fun setUpInfoMarkers()
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun setMessages(messageUiModel: MessageUiModel)

        fun onMapReady()
    }
}
