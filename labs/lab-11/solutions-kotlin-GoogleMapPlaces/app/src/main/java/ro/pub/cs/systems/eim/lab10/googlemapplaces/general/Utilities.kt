package ro.pub.cs.systems.eim.lab10.googlemapplaces.general

import com.google.android.gms.maps.model.BitmapDescriptorFactory

object Utilities {
    fun getDefaultMarker(position: Int): Float {
        return when (position) {
            Constants.MAGENTA_POSITION -> BitmapDescriptorFactory.HUE_MAGENTA
            Constants.VIOLET_POSITION -> BitmapDescriptorFactory.HUE_VIOLET
            Constants.ORANGE_POSITION -> BitmapDescriptorFactory.HUE_ORANGE
            Constants.RED_POSITION -> BitmapDescriptorFactory.HUE_RED
            Constants.BLUE_POSITION -> BitmapDescriptorFactory.HUE_BLUE
            Constants.GREEN_POSITION -> BitmapDescriptorFactory.HUE_GREEN
            Constants.AZURE_POSITION -> BitmapDescriptorFactory.HUE_AZURE
            Constants.ROSE_POSITION -> BitmapDescriptorFactory.HUE_ROSE
            Constants.CYAN_POSITION -> BitmapDescriptorFactory.HUE_CYAN
            Constants.YELLOW_POSITION -> BitmapDescriptorFactory.HUE_YELLOW
            else -> BitmapDescriptorFactory.HUE_RED
        }
    }
}
