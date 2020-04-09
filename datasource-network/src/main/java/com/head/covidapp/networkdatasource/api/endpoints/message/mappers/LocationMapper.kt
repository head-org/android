package com.head.covidapp.networkdatasource.api.endpoints.message.mappers

import com.head.covidapp.domain.models.message.LocationModel
import com.head.covidapp.domain.arch.mappers.DoubleMapper
import com.head.covidapp.networkdatasource.api.endpoints.message.model.LocationApiModel

class LocationMapper :
    DoubleMapper<LocationApiModel?, LocationModel> {

    override fun modelToApiModel(model: LocationModel): LocationApiModel =
        LocationApiModel(
            model.latitude,
            model.longitude
        )

    override fun apiModelToModel(apiModel: LocationApiModel?): LocationModel =
        LocationModel(
            apiModel?.latitude ?: 0.0,
            apiModel?.longitude ?: 0.0
        )
}
