package com.head.covidapp.domain.arch.mappers

interface DoubleMapper<ApiModel, Model> :
    SingleMapper<ApiModel, Model> {

    fun modelToApiModel(model: Model): ApiModel
}
