package com.head.covidapp.feature.main.ui.splash

import com.head.covidapp.feature.commons.arch.contract.BaseContract
import com.head.covidapp.feature.main.ui.model.MessageUiModel

interface SplashContract {

    interface View : BaseContract.View {

        fun navigateToMapFragment(messageUiModel: MessageUiModel)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun getMessages()
    }
}
