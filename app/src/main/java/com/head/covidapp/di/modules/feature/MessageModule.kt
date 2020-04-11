package com.head.covidapp.di.modules.feature

import com.head.covidapp.data.datasources.MessageApiDataSource
import com.head.covidapp.data.repositories.MessageRepositoryImpl
import com.head.covidapp.domain.repositories.MessageRepository
import com.head.covidapp.domain.usecases.GetMessagesUseCase
import com.head.covidapp.domain.usecases.PostMessageUseCase
import com.head.covidapp.networkdatasource.api.endpoints.message.MessageApiDataSourceImpl
import com.head.covidapp.networkdatasource.api.endpoints.message.mappers.LocationMapper
import com.head.covidapp.networkdatasource.api.endpoints.message.mappers.MessageMapper
import com.head.covidapp.networkdatasource.api.endpoints.message.mappers.MessagesMapper
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
