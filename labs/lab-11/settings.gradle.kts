pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "Laborator11"

// Include labtasks projects
include(":labtasks-google-map-places-app")
project(":labtasks-google-map-places-app").projectDir = file("labtasks/GoogleMapPlaces/app")

include(":labtasks-google-maps-geocoding-app")
project(":labtasks-google-maps-geocoding-app").projectDir = file("labtasks/GoogleMapsGeocoding/app")

include(":labtasks-google-maps-geofencing-app")
project(":labtasks-google-maps-geofencing-app").projectDir = file("labtasks/GoogleMapsGeofencing/app")

include(":gson-2.8.0")
project(":gson-2.8.0").projectDir = file("labtasks/GoogleMapsGeofencing/gson-2.8.0")

include(":labtasks-google-maps-location-update-app")
project(":labtasks-google-maps-location-update-app").projectDir = file("labtasks/GoogleMapsLocationUpdate/app")

// Include solutions projects
include(":solutions-google-map-places-app")
project(":solutions-google-map-places-app").projectDir = file("solutions/GoogleMapPlaces/app")

include(":solutions-google-maps-geocoding-app")
project(":solutions-google-maps-geocoding-app").projectDir = file("solutions/GoogleMapsGeocoding/app")

include(":solutions-google-maps-geofencing-app")
project(":solutions-google-maps-geofencing-app").projectDir = file("solutions/GoogleMapsGeofencing/app")

include(":solutions-gson-2.8.0")
project(":solutions-gson-2.8.0").projectDir = file("solutions/GoogleMapsGeofencing/gson-2.8.0")

include(":solutions-google-maps-location-update-app")
project(":solutions-google-maps-location-update-app").projectDir = file("solutions/GoogleMapsLocationUpdate/app")

// Include Kotlin projects
include(":labtasks-kotlin-google-map-places-app")
project(":labtasks-kotlin-google-map-places-app").projectDir = file("labtasks-kotlin-GoogleMapPlaces/app")

include(":solutions-kotlin-google-map-places-app")
project(":solutions-kotlin-google-map-places-app").projectDir = file("solutions-kotlin-GoogleMapPlaces/app")
