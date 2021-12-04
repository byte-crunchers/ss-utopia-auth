package com.smoothstack.Auth.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="users")  //name of table in RDS
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private int active;

    private Boolean is_admin;

    protected User(){}

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getActive() {
        return active;
    }

    //the db only has boolean is_admin, so convert that to a string
    public String getRoles() {
    	if(is_admin)
    		return "ADMIN";
    	else
    		return "USER";
    }

    //the db doesn't have a column for permissions
    public String getPermissions() {
        return "";
    }

    public List<String> getRoleList(){
    	if(is_admin)
    		return Arrays.asList(new String[] {"ADMIN"});
    	else
    		return Arrays.asList(new String[] {"USER"});
    }

    public List<String> getPermissionList(){
        return new ArrayList<>();
    }
}
