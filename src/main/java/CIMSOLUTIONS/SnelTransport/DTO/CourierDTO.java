package CIMSOLUTIONS.SnelTransport.DTO;

import java.util.List;

public class CourierDTO {

    //declare variables
    private int id;
    private String username;
    private List<String> packageSize;

    //getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getPackageSize() {
        return packageSize;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPackageSize(List<String> packageSize) {
        this.packageSize = packageSize;
    }
}
