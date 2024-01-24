package com.driver.model;


import javax.persistence.*;

@Entity
@Table(name = "cab")
public class Cab{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cabId;

    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Integer getCabId() {
        return cabId;
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

    public void setCabId(Integer cabId) {
        this.cabId = cabId;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}