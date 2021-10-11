package CIMSOLUTIONS.SnelTransport.DTO;

import CIMSOLUTIONS.SnelTransport.Models.Address;

public class PickUpAPIDTO {
    private int id;
    private String url;
    private Address address;
    private boolean isDisabled;

    public PickUpAPIDTO() {
    }
    public PickUpAPIDTO(int id, String url, Address address, boolean isDisabled) {
        this.id = id;
        this.url = url;
        this.address = address;
        this.isDisabled = isDisabled;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isDisabled() {
        return isDisabled;
    }
}
