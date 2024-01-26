package com.driver.model;


import javax.persistence.*;

@Entity
@Table(name = "tripBooking")
public class TripBooking{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tripBookingId;

    private String fromLocation;

    private String toLocation;

    private Integer distanceInKm;

    private TripStatus status;

    private Integer bill;

    @JoinColumn
    @ManyToOne
    private Customer customer;

    @JoinColumn
    @ManyToOne
    private Driver driver;
    public TripBooking() {
    }

    public TripBooking( String fromLocation, String toLocation, Integer distanceInKm, TripStatus status) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.distanceInKm = distanceInKm;
        this.status = status;
    }

    public TripBooking(String fromLocation, String toLocation, Integer distanceInKm, TripStatus status, Integer bill, Customer customer, Driver driver) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.distanceInKm = distanceInKm;
        this.status = status;
        this.bill = bill;
        this.customer = customer;
        this.driver = driver;
    }

    public Integer getTripBookingId() {
        return tripBookingId;
    }

    public Integer getBill() {
        return bill;
    }

    public void setBill(Integer bill) {
        this.bill = bill;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public Integer getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(Integer distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public void setTripBookingId(Integer tripBookingId) {
        this.tripBookingId = tripBookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }


}