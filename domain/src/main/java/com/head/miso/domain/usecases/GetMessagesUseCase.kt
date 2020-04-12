package com.head.miso.domain.usecases

import com.head.miso.domain.arch.callback.Response
import com.head.miso.domain.models.message.MessageModel
import com.head.miso.domain.repositories.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMessagesUseCase(private val messageRepository: MessageRepository) {

    suspend operator fun invoke(): Response<List<MessageModel>> =
        withContext(Dispatchers.IO) { messageRepository.getMessages() }
}
