package com.head.covidapp.main.ui.splash

import com.head.covidapp.domain.arch.callback.onCallback
import com.head.covidapp.domain.usecases.GetMessagesUseCase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashPresenter(val getMessagesUseCase: GetMessagesUseCase) : SplashContract.Presenter,
    CoroutineScope {

    override var view: SplashContract.View? = null
    private val job: Job = SupervisorJob()
    private val errorHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception -> exception.stackTrace }

    override val coroutineContext: CoroutineContext = job + Dispatchers.Main + errorHandler

    override fun getMessages() {
        launch {
            getMessagesUseCase().onCallback(
                {
                    view?.navigateToMapFragment(it)
                },
                {
                    view?.navigateToMapFragment(null)
                }
            )
        }
    }

    override fun detachView() {
        super.detachView()

        job.cancel()
    }
}
