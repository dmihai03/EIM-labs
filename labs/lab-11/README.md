# Laborator11

## Project Setup

This project has been configured as a multi-project Android Gradle workspace with separate run configurations for labtasks and solutions projects, each containing 4 Google Maps applications.

## Project Structure

Eight separate Android applications plus two gson library modules:
- labtasks/GoogleMapPlaces
- labtasks/GoogleMapsGeocoding
- labtasks/GoogleMapsGeofencing (with gson-2.8.0 module)
- labtasks/GoogleMapsLocationUpdate
- solutions/GoogleMapPlaces
- solutions/GoogleMapsGeocoding
- solutions/GoogleMapsGeofencing (with gson-2.8.0 module)
- solutions/GoogleMapsLocationUpdate

## Configuration Details

### Multi-Project Gradle Configuration
- Root-level settings.gradle.kts configures 10 projects (8 apps + 2 gson modules)
- Root-level build.gradle.kts with common plugin configuration
- Gradle wrapper files copied to root for unified build management

### Version Control Cleanup
- Comprehensive .gitignore with Android project rules
- Removed 8 local.properties files
- Removed 15 .iml files
- Removed all build directories

### UI Layout Improvements
- Added android:padding="8dp" to all main LinearLayout containers
- Improved spacing and visual consistency across all Google Maps activities

## Usage

### Build Projects
```bash
# Labtasks
./gradlew :labtasks-google-map-places-app:build
./gradlew :labtasks-google-maps-geocoding-app:build
./gradlew :labtasks-google-maps-geofencing-app:build
./gradlew :labtasks-google-maps-location-update-app:build

# Solutions
./gradlew :solutions-google-map-places-app:build
./gradlew :solutions-google-maps-geocoding-app:build
./gradlew :solutions-google-maps-geofencing-app:build
./gradlew :solutions-google-maps-location-update-app:build
```

### View Project Structure
```bash
./gradlew projects
```

## Applications

All applications demonstrate Google Maps integration:
- **GoogleMapPlaces**: Add and manage custom places on the map
- **GoogleMapsGeocoding**: Convert addresses to coordinates and vice versa
- **GoogleMapsGeofencing**: Set up geofences and monitor location events
- **GoogleMapsLocationUpdate**: Track and display real-time location updates

## Result
✅ Multi-project Gradle workspace configured
✅ 8 separate run configurations (4 labtasks + 4 solutions)
✅ Clean version control setup
✅ Improved UI layouts
✅ All projects build successfully
