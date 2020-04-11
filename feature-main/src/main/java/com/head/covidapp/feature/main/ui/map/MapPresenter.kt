package com.head.covidapp.feature.main.ui.map

import android.util.Log
import com.head.covidapp.feature.main.ui.model.MessageUiModel

class MapPresenter : MapContract.Presenter {

    override var view: MapContract.View? = null
    private lateinit var messageUiModel: MessageUiModel
    private lateinit var userLocation: Pair<Double, Double>

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
        userLocation = location
        view?.refreshMap(location)
    }

    override fun onLocationError() {
        view?.showErrorLocation()
    }

    override fun onFloatingButtonClicked() {
        view?.showDialog()
    }

    override fun onSaveMessageClicked(message: Pair<String, String>?) {
        message?.let {
            publishMessage(message, userLocation)
        }
    }

    private fun publishMessage(message: Pair<String, String>, userLocation: Pair<Double, Double>) {
        Log.d("TITLE: ", message.first)
        Log.d("MENSAJE: ", message.second)
    }
}
