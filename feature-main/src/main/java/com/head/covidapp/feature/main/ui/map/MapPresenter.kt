package com.head.covidapp.feature.main.ui.map

class MapPresenter : MapContract.Presenter {

    override var view: MapContract.View? = null

    override fun onMapReady() {
        if (view?.checkPermissions() == true) {
            view?.addMarkers()
        } else {
            view?.requestPermissions()
        }
    }
}
