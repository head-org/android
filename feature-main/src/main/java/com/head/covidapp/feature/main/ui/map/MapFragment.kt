package com.head.covidapp.feature.main.ui.map

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.head.covidapp.extensions.checkPermissions
import com.head.covidapp.extensions.requestPermission
import com.head.covidapp.feature.main.R
import kotlinx.android.synthetic.main.maps_fragment.*
import org.koin.android.ext.android.inject

class MapFragment : Fragment(R.layout.maps_fragment), MapContract.View, OnMapReadyCallback {

    private var googleMap: GoogleMap? = null
    private lateinit var location: FusedLocationProviderClient
    private val presenter: MapContract.Presenter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)

        map.onCreate(savedInstanceState)
        map.getMapAsync(this)

        floatingActionButton.setOnClickListener {
        }
    }

    override fun onStart() {
        super.onStart()

        map.onStart()
    }

    override fun onResume() {
        super.onResume()

        map.onResume()
    }

    override fun onPause() {
        super.onPause()

        map.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()

        map.onDestroy()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap
        presenter.onMapReady()
    }

    override fun checkPermissions(): Boolean = context?.checkPermissions(COARSE_PERMISSION) ?: false

    override fun requestPermissions() {
        activity?.requestPermission(COARSE_PERMISSION)
    }

    override fun addMarkers() {
    }

    private companion object {
        const val COARSE_PERMISSION = Manifest.permission.ACCESS_COARSE_LOCATION
    }
}
