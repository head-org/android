package com.head.covidapp.feature.main.ui.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.location.LocationManager
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.head.covidapp.extensions.checkPermissions
import com.head.covidapp.extensions.requestPermission
import com.head.covidapp.feature.main.ui.model.MessageUiModel
import com.head.covidapp.main.R
import kotlinx.android.synthetic.main.map_fragment.*
import org.koin.android.ext.android.inject


class MapFragment : Fragment(R.layout.map_fragment), MapContract.View, OnMapReadyCallback {

    private val presenter: MapContract.Presenter by inject()
    private var googleMap: GoogleMap? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
        presenter.setMessages(arguments?.getSerializable(MESSAGES) as MessageUiModel)

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

    override fun showProgress() {
        progressBar.show()
    }

    override fun hideProgress() {
        progressBar.hide()
    }

    override fun disableIndicatorsButtons() {
        googleMap?.uiSettings?.isMapToolbarEnabled = false
    }

    override fun addMarkers(messageUiModel: MessageUiModel) {
        messageUiModel.messageList.forEach {
            googleMap?.addMarker(
                MarkerOptions()
                    .position(
                        LatLng(
                            it.location.latitude,
                            it.location.longitude
                        )
                    )
                    .title(it.title)
                    .snippet(it.content)
            )
        }
    }

    override fun setUpInfoMarkers() {
        googleMap?.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
            override fun getInfoContents(marker: Marker?): View? {
                val info = LinearLayout(context)
                info.orientation = LinearLayout.VERTICAL

                val title = TextView(context)
                title.gravity = Gravity.CENTER
                title.setTypeface(null, Typeface.BOLD)
                title.text = marker?.title

                val snippet = TextView(context)
                snippet.text = marker?.snippet

                info.addView(title)
                info.addView(snippet)

                return info
            }

            override fun getInfoWindow(p0: Marker?): View? = null
        })
    }

    override fun checkPermissions(): Boolean =
        context?.let {
            it.checkPermissions(COARSE_PERMISSION) && it.checkPermissions(FINE_PERMISSION)
        } ?: false

    override fun requestPermissions() {
        activity?.requestPermission(arrayOf(COARSE_PERMISSION, FINE_PERMISSION), PERMISSION_ID)
    }

    override fun showErrorPermissions() {
        view?.let {
            Snackbar.make(it, R.string.permissions_error, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            presenter.onPermissionsGranted()
        } else {
            presenter.onPermissionsRejected()
        }
    }

    override fun checkGps(): Boolean {
        val locationManager: LocationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    override fun showErrorGps() {
        // TODO: Change for dialog to request user to active location and intent to phone settings
        view?.let {
            Snackbar.make(it, R.string.location_gps_error, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun getUserLocation() {
        activity?.let {
            fusedLocationProviderClient = FusedLocationProviderClient(it)
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    presenter.onLocationFinished(Pair(location.latitude, location.longitude))
                } else {
                    presenter.onLocationError()
                }
            }
        }
    }

    override fun showErrorLocation() {
        view?.let {
            Snackbar.make(it, R.string.location_error, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun refreshMap(location: Pair<Double, Double>) {
        val position = LatLng(location.first, location.second)
        googleMap?.apply {
            val cameraPosition: CameraPosition = CameraPosition.Builder()
                .target(position)
                .zoom(ZOOM_LEVEL)
                .build()
            this.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            this.uiSettings.apply {
                this.isZoomGesturesEnabled = false
                this.isScrollGesturesEnabled = false
            }
        }
    }

    companion object {
        const val COARSE_PERMISSION = Manifest.permission.ACCESS_COARSE_LOCATION
        const val FINE_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION
        const val MESSAGES = "messages"
        const val PERMISSION_ID = 31
        const val ZOOM_LEVEL = 15F
    }
}
