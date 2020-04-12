package com.head.miso.domain.arch.mappers

interface DoubleMapper<ApiModel, Model> :
    SingleMapper<ApiModel, Model> {

    fun modelToApiModel(model: Model): ApiModel
}
