package ro.pub.cs.systems.eim.lab10.googlemapplaces.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ro.pub.cs.systems.eim.lab10.R
import ro.pub.cs.systems.eim.lab10.googlemapplaces.controller.PlacesAdapter
import ro.pub.cs.systems.eim.lab10.googlemapplaces.general.Constants
import ro.pub.cs.systems.eim.lab10.googlemapplaces.general.Utilities
import ro.pub.cs.systems.eim.lab10.googlemapplaces.model.Place
import java.util.ArrayList

class GoogleMapsActivity : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private var googleMap: GoogleMap? = null
    private var googleApiClient: GoogleApiClient? = null

    private var latitudeEditText: EditText? = null
    private var longitudeEditText: EditText? = null
    private var navigateToLocationButton: Button? = null

    private var nameEditText: EditText? = null

    private var markerTypeSpinner: Spinner? = null

    private var placesSpinner: Spinner? = null
    private var places: MutableList<Place>? = null
    private var placesAdapter: PlacesAdapter? = null

    private var addPlaceButton: Button? = null
    private var clearPlacesButton: Button? = null

    private fun navigateToLocation(latitude: Double, longitude: Double) {
        latitudeEditText?.setText(latitude.toString())
        longitudeEditText?.setText(longitude.toString())
        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(latitude, longitude))
            .zoom(Constants.CAMERA_ZOOM.toFloat())
            .build()
        googleMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    private val navigateToLocationButtonListener = object : View.OnClickListener {
        override fun onClick(view: View) {
            val latitudeContent = latitudeEditText?.text?.toString() ?: ""
            val longitudeContent = longitudeEditText?.text?.toString() ?: ""

            if (latitudeContent.isEmpty() || longitudeContent.isEmpty()) {
                Toast.makeText(applicationContext, "GPS Coordinates should be filled!", Toast.LENGTH_SHORT).show()
                return
            }

            val latitudeValue = latitudeContent.toDouble()
            val longitudeValue = longitudeContent.toDouble()
            navigateToLocation(latitudeValue, longitudeValue)
        }
    }

    private val placesSpinnerListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val place = placesAdapter?.getItem(position) as? Place ?: return
            val latitude = place.latitude
            val longitude = place.longitude
            latitudeEditText?.setText(latitude.toString())
            longitudeEditText?.setText(longitude.toString())
            nameEditText?.setText(place.name)
            navigateToLocation(latitude, longitude)
        }

        override fun onNothingSelected(adapterView: AdapterView<*>?) {}
    }

    private val addPlaceButtonListener = object : View.OnClickListener {
        override fun onClick(view: View) {
            val latitudeContent = latitudeEditText?.text?.toString() ?: ""
            val longitudeContent = longitudeEditText?.text?.toString() ?: ""
            val nameContent = nameEditText?.text?.toString() ?: ""

            if (latitudeContent.isEmpty() || longitudeContent.isEmpty() || nameContent.isEmpty()) {
                Toast.makeText(applicationContext, "GPS Coordinates / Name should be filled!", Toast.LENGTH_LONG).show()
                return
            }

            val latitudeValue = latitudeContent.toDouble()
            val longitudeValue = longitudeContent.toDouble()
            navigateToLocation(latitudeValue, longitudeValue)

            val markerOptions = MarkerOptions()
                .position(LatLng(latitudeValue, longitudeValue))
                .title(nameContent)
                .icon(BitmapDescriptorFactory.defaultMarker(Utilities.getDefaultMarker(markerTypeSpinner?.selectedItemPosition ?: 0)))

            googleMap?.addMarker(markerOptions)

            places?.add(Place(
                latitudeValue,
                longitudeValue,
                nameContent,
                Utilities.getDefaultMarker(markerTypeSpinner?.selectedItemPosition ?: 0)
            ))
            placesAdapter?.notifyDataSetChanged()
        }
    }

    private val clearPlacesButtonListener = object : View.OnClickListener {
        override fun onClick(view: View) {
            if (places == null || places!!.isEmpty()) {
                Toast.makeText(applicationContext, "There are no places available!", Toast.LENGTH_LONG).show()
                return
            }

            googleMap?.clear()

            places?.clear()
            placesAdapter?.notifyDataSetChanged()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_maps)

        Log.i(Constants.TAG, "onCreate() callback method was invoked")

        latitudeEditText = findViewById(R.id.latitude_edit_text)
        longitudeEditText = findViewById(R.id.longitude_edit_text)
        navigateToLocationButton = findViewById(R.id.navigate_to_location_button)
        navigateToLocationButton?.setOnClickListener(navigateToLocationButtonListener)

        nameEditText = findViewById(R.id.name_edit_text)

        markerTypeSpinner = findViewById(R.id.marker_type_spinner)

        placesSpinner = findViewById(R.id.places_spinner)
        places = ArrayList()
        placesAdapter = PlacesAdapter(this, places!!)
        placesSpinner?.adapter = placesAdapter
        placesSpinner?.onItemSelectedListener = placesSpinnerListener

        addPlaceButton = findViewById(R.id.add_place_button)
        addPlaceButton?.setOnClickListener(addPlaceButtonListener)

        clearPlacesButton = findViewById(R.id.clear_places_button)
        clearPlacesButton?.setOnClickListener(clearPlacesButtonListener)

        googleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()
    }

    override fun onStart() {
        super.onStart()
        Log.i(Constants.TAG, "onStart() callback method was invoked")
        if (googleApiClient != null && !googleApiClient!!.isConnected) {
            googleApiClient!!.connect()
        }
        if (googleMap == null) {
            (supportFragmentManager.findFragmentById(R.id.google_map) as? SupportMapFragment)?.getMapAsync(object : OnMapReadyCallback {
                override fun onMapReady(readyGoogleMap: GoogleMap) {
                    googleMap = readyGoogleMap
                }
            })
        }
    }

    override fun onStop() {
        Log.i(Constants.TAG, "onStop() callback method was invoked")
        if (googleApiClient != null && googleApiClient!!.isConnected) {
            googleApiClient!!.disconnect()
        }
        super.onStop()
    }

    override fun onDestroy() {
        Log.i(Constants.TAG, "onDestroy() callback method was invoked")
        googleApiClient = null
        super.onDestroy()
    }

    override fun onConnected(connectionHint: Bundle?) {
        Log.i(Constants.TAG, "onConnected() callback method has been invoked")
    }

    override fun onConnectionSuspended(cause: Int) {
        Log.i(Constants.TAG, "onConnectionSuspended() callback method has been invoked with cause $cause")
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.i(Constants.TAG, "onConnectionFailed() callback method has been invoked")
    }
}
