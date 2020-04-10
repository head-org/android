package com.head.covidapp.domain.usecases

import com.head.covidapp.domain.arch.callback.Response
import com.head.covidapp.domain.models.message.MessageModel
import com.head.covidapp.domain.repositories.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMessagesUseCase(private val messageRepository: MessageRepository) {

    suspend operator fun invoke(): Response<List<MessageModel>> =
        withContext(Dispatchers.IO) { messageRepository.getMessages() }
}
