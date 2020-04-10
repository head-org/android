package com.head.covidapp.feature.main.ui.map

import android.Manifest
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.head.covidapp.extensions.checkPermissions
import com.head.covidapp.extensions.requestPermission
import com.head.covidapp.feature.main.ui.model.MessageUiModel
import com.head.covidapp.main.R
import kotlinx.android.synthetic.main.map_fragment.*
import org.koin.android.ext.android.inject


class MapFragment : Fragment(R.layout.map_fragment), MapContract.View, OnMapReadyCallback {

    private val presenter: MapContract.Presenter by inject()
    private lateinit var messages: MessageUiModel
    private var googleMap: GoogleMap? = null
    private lateinit var location: FusedLocationProviderClient

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

    override fun checkPermissions(): Boolean = context?.checkPermissions(COARSE_PERMISSION) ?: false

    override fun requestPermissions() {
        activity?.requestPermission(COARSE_PERMISSION)
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

    companion object {
        const val COARSE_PERMISSION = Manifest.permission.ACCESS_COARSE_LOCATION
        const val MESSAGES = "messages"
    }
}
