package com.head.covidapp.feature.main.ui.map

import com.head.covidapp.feature.main.ui.model.MessageUiModel

class MapPresenter : MapContract.Presenter {

    override var view: MapContract.View? = null
    lateinit var messageUiModel: MessageUiModel

    override fun attachView(view: MapContract.View) {
        super.attachView(view)

        view.showProgress()
    }

    override fun setMessages(messageUiModel: MessageUiModel) {
        this.messageUiModel = messageUiModel
    }

    override fun onMapReady() {
        view?.disableIndicatorsButtons()
        takeIf { messageUiModel.messageList.isNotEmpty() }.let {
            view?.apply {
                this.addMarkers(messageUiModel)
                this.setUpInfoMarkers()
                this.hideProgress()
            }
        }
        /*if (view?.checkPermissions() == true) {
            view?.addMarkers()
        } else {
            view?.requestPermissions()
        }*/
    }
}
