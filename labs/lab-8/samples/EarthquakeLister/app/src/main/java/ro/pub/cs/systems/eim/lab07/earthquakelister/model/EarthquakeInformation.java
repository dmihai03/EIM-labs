package ro.pub.cs.systems.eim.lab07.earthquakelister.model;

public class EarthquakeInformation {

    private final double latitude;
    private final double longitude;
    private final double magnitude;
    private final double depth;
    private final String source;
    private final String dateAndTime;

    public EarthquakeInformation(double latitude, double longitude, double magnitude, double depth, String source, String dateAndTime) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.magnitude = magnitude;
        this.depth = depth;
        this.source = source;
        this.dateAndTime = dateAndTime;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public double getDepth() {
        return depth;
    }

    public String getSource() {
        return source;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

}
