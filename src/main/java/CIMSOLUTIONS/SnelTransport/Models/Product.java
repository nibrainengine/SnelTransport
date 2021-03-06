package CIMSOLUTIONS.SnelTransport.Models;

import java.util.List;

public class Product {

    //declare variables
    private int id;
    private String supplierNumberIdentification;
    private String name;
    private Double price;
    private String size;
    private List<String> categories;

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplierNumberIdentification() {
        return supplierNumberIdentification;
    }

    public void setSupplierNumberIdentification(String supplierNumberIdentification) {
        this.supplierNumberIdentification = supplierNumberIdentification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }


}
