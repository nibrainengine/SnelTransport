package class_objects;

import java.util.List;

public class Product {

    enum ProductSize{
        Klein,
        Middel,
        Groot
    }

    enum Category{

    }

    //declare variables
    private int id;
    private String suplierNumberIdentification;
    private String name;
    private Double price;
    private ProductSize size;
    private List<Category> categories;
}
