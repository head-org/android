package com.head.covidapp.main.ui.splash

import com.head.covidapp.domain.models.message.MessageModel
import com.head.covidapp.feature.commons.arch.contract.BaseContract

interface SplashContract {

    interface View : BaseContract.View {

        fun navigateToMapFragment(messageList: List<MessageModel>?)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun getMessages()
    }
}
