package CIMSOLUTIONS.SnelTransport.Models;

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

    public Address(String street, String houseNumber, String zipCode, String city, String country, Double latitude, Double longitude) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public Address(String street, String houseNumber, String zipCode, String city, String country, Double latitude, Double longitude, int id) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
        this.id = id;
    }

    public Address() {
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
