package class_objects;

import java.util.Date;

public class User {

    enum Role{

    }

    //declare variables
    private int id;
    private String username;
    private Boolean isDisabled;
    private String email;
    private boolean emailConfirmed;
    private String phoneNumber;
    private Boolean phoneNumberConfirmed;
    private String fullName;
    private Date createdAt;
    private Role role;
}
