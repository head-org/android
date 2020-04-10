package com.head.covidapp.domain.arch.mappers

interface SingleMapper<ApiModel, Model> {

    fun apiModelToModel(apiModel: ApiModel): Model
}
