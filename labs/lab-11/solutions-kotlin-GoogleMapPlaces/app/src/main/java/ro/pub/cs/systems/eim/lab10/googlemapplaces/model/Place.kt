package ro.pub.cs.systems.eim.lab10.googlemapplaces.model

import com.google.android.gms.maps.model.BitmapDescriptorFactory

class Place {
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var name: String = ""
    var markerType: Float = BitmapDescriptorFactory.HUE_RED

    constructor() {
        latitude = 0.0
        longitude = 0.0
        name = ""
        markerType = BitmapDescriptorFactory.HUE_RED
    }

    constructor(latitude: Double, longitude: Double, name: String, markerType: Float) {
        this.latitude = latitude
        this.longitude = longitude
        this.name = name
        this.markerType = markerType
    }

    override fun toString(): String {
        return "$name($latitude, $longitude)"
    }
}
