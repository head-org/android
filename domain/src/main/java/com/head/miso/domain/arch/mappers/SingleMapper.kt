package com.head.miso.domain.arch.mappers

interface SingleMapper<ApiModel, Model> {

    fun apiModelToModel(apiModel: ApiModel): Model
}
