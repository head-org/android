package com.head.miso.feature.main.ui.splash

import com.head.miso.feature.commons.arch.contract.BaseContract
import com.head.miso.feature.main.ui.model.MessageUiModel

interface SplashContract {

    interface View : BaseContract.View {

        suspend fun startAnimation()

        fun navigateToMapFragment(messageUiModel: MessageUiModel)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun getMessages()
    }
}
