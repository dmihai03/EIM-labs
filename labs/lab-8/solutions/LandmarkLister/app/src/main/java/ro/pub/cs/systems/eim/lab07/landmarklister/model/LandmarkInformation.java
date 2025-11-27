package ro.pub.cs.systems.eim.lab07.landmarklister.model;

public class LandmarkInformation {

    private final double latitude;
    private final double longitude;
    private final String toponymName;
    private final long population;
    private final String codeName;
    private final String name;
    private final String wikipediaWebPageAddressName;
    private final String countryCode;

    public LandmarkInformation(double latitude, double longitude, String toponymName, long population, String codeName, String name, String wikipediaWebPageAddressName, String countryCode) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.toponymName = toponymName;
        this.population = population;
        this.codeName = codeName;
        this.name = name;
        this.wikipediaWebPageAddressName = wikipediaWebPageAddressName;
        this.countryCode = countryCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getToponymName() {
        return toponymName;
    }

    public long getPopulation() {
        return population;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getName() {
        return name;
    }

    public String getWikipediaWebPageAddressName() {
        return wikipediaWebPageAddressName;
    }

    public String getCountryCode() {
        return countryCode;
    }

}
