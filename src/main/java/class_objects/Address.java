package class_objects;

public class Address {

    //declare variables
    private int id;
    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String country;
    private Double longitude;
    private Double latitude;

    public Address(int id, String street, String houseNumber, String zipCode, String city, String country, Double longitude, Double latitude) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }
}
