package com.head.miso.di.modules.feature

import com.head.miso.data.datasources.MessageApiDataSource
import com.head.miso.data.repositories.MessageRepositoryImpl
import com.head.miso.domain.repositories.MessageRepository
import com.head.miso.domain.usecases.GetMessagesUseCase
import com.head.miso.domain.usecases.PostMessageUseCase
import com.head.miso.networkdatasource.api.endpoints.message.MessageApiDataSourceImpl
import com.head.miso.networkdatasource.api.endpoints.message.mappers.LocationMapper
import com.head.miso.networkdatasource.api.endpoints.message.mappers.MessageMapper
import com.head.miso.networkdatasource.api.endpoints.message.mappers.MessagesMapper
import org.koin.dsl.module

val messageModule = module {

    // UseCases
    single { GetMessagesUseCase(get()) }
    single { PostMessageUseCase(get()) }

    // Repositories
    single<MessageRepository> { MessageRepositoryImpl(get()) }

    // DataSources
    single<MessageApiDataSource> { MessageApiDataSourceImpl(get(), get(), get()) }

    // Mappers
    single { MessagesMapper(get()) }
    single { MessageMapper(get()) }
    single { LocationMapper() }
}
