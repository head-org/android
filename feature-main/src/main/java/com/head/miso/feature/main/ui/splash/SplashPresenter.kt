package com.head.miso.feature.main.ui.splash

import com.head.miso.domain.arch.callback.onCallback
import com.head.miso.domain.usecases.GetMessagesUseCase
import com.head.miso.feature.main.ui.mapper.MapperMessageUiModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashPresenter(
    val getMessagesUseCase: GetMessagesUseCase,
    private val mapperMessageUiModel: MapperMessageUiModel
) : SplashContract.Presenter, CoroutineScope {

    override var view: SplashContract.View? = null
    private val job: Job = SupervisorJob()
    private val errorHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception -> exception.stackTrace }

    override val coroutineContext: CoroutineContext = job + Dispatchers.Main + errorHandler

    override fun getMessages() {
        launch {
            val animatorDeferred = async {
                view?.startAnimation()
            }
            val messageListDeferred = async {
                getMessagesUseCase()
            }

            animatorDeferred.await()
            messageListDeferred.await()
                .onCallback(
                    {
                        view?.navigateToMapFragment(mapperMessageUiModel.map(it))
                    },
                    {
                        view?.navigateToMapFragment(mapperMessageUiModel.map(null))
                    }
                )
        }
    }

    override fun detachView() {
        super.detachView()

        job.cancel()
    }
}
