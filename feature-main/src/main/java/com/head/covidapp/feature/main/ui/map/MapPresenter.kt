package com.head.covidapp.feature.main.ui.map

import com.head.covidapp.domain.arch.callback.onCallback
import com.head.covidapp.domain.models.message.MessageModel
import com.head.covidapp.domain.usecases.PostMessageUseCase
import com.head.covidapp.feature.main.ui.model.MessageUiModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MapPresenter(private val postMessageUseCase: PostMessageUseCase) : MapContract.Presenter,
    CoroutineScope {

    override var view: MapContract.View? = null
    private var messages = mutableListOf<MessageModel>()
    private lateinit var userLocation: Pair<Double, Double>
    private val job: Job = SupervisorJob()
    private val errorHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception -> exception.stackTrace }

    override val coroutineContext: CoroutineContext = job + Dispatchers.Main + errorHandler

    override fun attachView(view: MapContract.View) {
        super.attachView(view)

        view.showProgress()
    }

    override fun setMessages(messageUiModel: MessageUiModel) {
        this.messages = messageUiModel.messageList.toMutableList()
    }

    override fun onMapReady() {
        takeIf { messages.isNotEmpty() }?.let {
            view?.apply {
                messages.forEach { this.addMarker(it) }
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
        launch {
            view?.showProgress()
            postMessageUseCase(message, userLocation).onCallback(
                {
                    messages.add(it)
                    view?.addMarker(it)
                },
                {
                    view?.onErrorPostMessage()
                }
            )
            view?.hideProgress()
        }
    }

    override fun detachView() {
        super.detachView()

        job.cancel()
    }
}
