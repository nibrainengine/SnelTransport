package class_objects;

import java.util.List;

public class Product {

    enum ProductSize{
        Klein,
        Middel,
        Groot
    }

    public enum Category{

    }

    //declare variables
    private int id;
    private String suplierNumberIdentification;
    private String name;
    private Double price;
    private String size;
    private String categories;

    public Product(int id, String name, Double price, String size, String categories) {
        this.id = id;
        //this.suplierNumberIdentification = suplierNumberIdentification;
        this.name = name;
        this.price = price;
        this.size = size;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public String getSuplierNumberIdentification() {
        return suplierNumberIdentification;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    public String getCategories() {
        return categories;
    }
}
