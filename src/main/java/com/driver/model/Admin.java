package com.driver.model;


import javax.persistence.*;


@Entity
@Table(name = "admin")

public class Admin{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer adminId;

    private String username;

    private String password;



    public Admin() {
    }

    public Admin(String userName, String password) {
        this.username = userName;
        this.password = password;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdminId(Integer adminId) {
        adminId = adminId;
    }
}