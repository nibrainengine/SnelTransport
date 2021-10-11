package CIMSOLUTIONS.SnelTransport.DTO;

import java.util.List;

public class CourierDTO {

    //declare variables
    private int id;
    private String fullName;
    private List<String> packageSize;

    //getters
    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public List<String> getPackageSize() {
        return packageSize;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPackageSize(List<String> packageSize) {
        this.packageSize = packageSize;
    }
}
