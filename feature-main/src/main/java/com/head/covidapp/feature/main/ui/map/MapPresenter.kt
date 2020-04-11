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
        takeIf { messageUiModel.messageList.isNotEmpty() }?.let {
            view?.apply {
                this.addMarkers(messageUiModel)
                this.configMarkers()
                this.hideProgress()
                takeIf { this.checkPermissions() }?.let {
                    manageLocation()
                } ?: this.requestPermissions()
            }
        } ?: view?.hideProgress()
    }

    override fun onPermissionsGranted() {
        manageLocation()
    }

    private fun manageLocation() {
        view?.let { view ->
            takeIf { view.checkGps() }?.let {
                view.getUserLocation()
            } ?: view.showErrorGps()
        }
    }

    override fun onPermissionsRejected() {
        view?.showErrorPermissions()
    }

    override fun onLocationFinished(location: Pair<Double, Double>) {
        view?.refreshMap(location)
    }

    override fun onLocationError() {
        view?.showErrorLocation()
    }
}
