package com.driver.model;


import javax.persistence.*;

@Entity
@Table(name = "cab")
public class Cab{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer perKmRate;

    private boolean available;

    @JoinColumn
    @OneToOne
    private Driver driver;
    public Cab() {
    }

    public Cab( Integer perKmRate, boolean available) {
        this.perKmRate = perKmRate;
        this.available = available;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPerKmRate() {
        return perKmRate;
    }

    public void setPerKmRate(Integer perKmRate) {
        this.perKmRate = perKmRate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}