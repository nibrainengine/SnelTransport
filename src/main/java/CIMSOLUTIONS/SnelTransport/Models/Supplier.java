package CIMSOLUTIONS.SnelTransport.Models;

import java.util.List;

public class Supplier extends Company{

    //declare variables
    private int id;
    private int reliability;
    private Boolean isDisabled;
    private List<SupplierApi> supplierApis;
    private List<SupplierItem> supplierItems;
}
