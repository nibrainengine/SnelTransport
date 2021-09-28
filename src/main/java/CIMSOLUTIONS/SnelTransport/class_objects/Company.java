package CIMSOLUTIONS.SnelTransport.class_objects;

import java.util.List;

public class Company {

    //declare variables
    private int id;
    private String name;
    private String email;
    private Boolean emailConfirmed;
    private Boolean isDisabled;
    private String phone;
    private int loadInTime;
    private int kvkNumber;
    private List<CompanyRepresentative> companyRepresentatives;
    private List<CompanyWorkPeriod> companyWorkPeriods;
    private Address companyAddress;
}
