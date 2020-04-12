package com.head.miso.networkdatasource.api.endpoints.message.mappers

import com.head.miso.domain.models.message.LocationModel
import com.head.miso.domain.arch.mappers.DoubleMapper
import com.head.miso.networkdatasource.api.endpoints.message.model.LocationApiModel

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
