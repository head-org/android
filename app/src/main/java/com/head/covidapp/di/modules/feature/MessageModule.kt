package com.head.covidapp.di.modules.feature

import com.head.covidapp.data.datasources.MessageApiDataSource
import com.head.covidapp.data.repositories.MessageRepositoryImpl
import com.head.covidapp.domain.repositories.MessageRepository
import com.head.covidapp.domain.usecases.GetMessagesUseCase
import com.head.covidapp.networkdatasource.api.endpoints.message.MessageApiDataSourceImpl
import com.head.covidapp.networkdatasource.api.endpoints.message.mappers.LocationMapper
import com.head.covidapp.networkdatasource.api.endpoints.message.mappers.MessageMapper
import org.koin.dsl.module

val messageModule = module {

    // UseCases
    single { GetMessagesUseCase(get()) }

    // Repositories
    single<MessageRepository> { MessageRepositoryImpl(get()) }

    // DataSources
    single<MessageApiDataSource> { MessageApiDataSourceImpl(get(), get()) }

    // Mappers
    single { MessageMapper(get()) }
    single { LocationMapper() }
}
