package CIMSOLUTIONS.SnelTransport.class_objects;

public class Product {

    //declare variables
    private int id;
    private String suplierNumberIdentification;
    private String name;
    private Double price;
    private String size;
    private String categories;

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuplierNumberIdentification() {
        return suplierNumberIdentification;
    }

    public void setSuplierNumberIdentification(String suplierNumberIdentification) {
        this.suplierNumberIdentification = suplierNumberIdentification;
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

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}
