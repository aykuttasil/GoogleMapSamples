package com.aykutasil.googlemapsamples

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**41.0292066
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        val fromLocLatLng = LatLng(41.0292066, 29.0444427)
        val toLocLatLng = LatLng(41.0352273, 28.9776891)
        /*
        mMap = googleMap

        val altunizade = LatLng(41.0292066, 29.0444427)
        mMap.addMarker(MarkerOptions().position(altunizade).title("Altunizade"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(altunizade))
        mMap.isMyLocationEnabled = true
        */
        mMap = googleMap
        val uiSettings = mMap.uiSettings
        uiSettings.isZoomControlsEnabled = true
        uiSettings.isMyLocationButtonEnabled = true
        uiSettings.isCompassEnabled = false
        //uiSettings.isMapToolbarEnabled = true

        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.custom_map_style))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fromLocLatLng, 12f))
        mMap.addMarker(MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_foreground))
                .position(fromLocLatLng)
                .flat(true)
                //.rotation(245f)
        )

        ButtonAnimateCamera.setOnClickListener {
            val cameraPosition = CameraPosition.builder()
                    .target(toLocLatLng)
                    .zoom(14f)
                    //.bearing(45f)
                    .build()
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 2000, null)
        }


        // Flat markers will rotate when the map is rotated,
        // and change perspective when the map is tilted.
//        mMap.addMarker(MarkerOptions()
//                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_foreground))
//                .position(altunizade)
//                .flat(true)
//                .rotation(245f));
//
//        val cameraPosition = CameraPosition.builder()
//                .target(altunizade)
//                .zoom(8f)
//                .bearing(90f)
//                .build()
//
//        // Animate the change in camera view over 2 seconds
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
//                5000, null);
    }
}
