package com.driver.model;


import javax.persistence.*;


@Entity
@Table(name = "admin")

public class Admin{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer AdminId;

    private String userName;

    private String password;



    public Admin() {
    }

    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Integer getAdminId() {
        return AdminId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}