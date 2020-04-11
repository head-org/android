package com.head.covidapp.domain.usecases

import com.head.covidapp.domain.arch.callback.Response
import com.head.covidapp.domain.models.message.MessageModel
import com.head.covidapp.domain.repositories.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostMessageUseCase(private val messageRepository: MessageRepository) {

    suspend operator fun invoke(
        message: Pair<String, String>,
        userLocation: Pair<Double, Double>
    ): Response<MessageModel> =
        withContext(Dispatchers.IO) { messageRepository.postMessage(message, userLocation) }
}
