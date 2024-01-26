package com.driver.services.impl;

import com.driver.model.*;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
        Customer customer  = customerRepository2.findById(customerId).get();
		customerRepository2.delete(customer);
	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
        List<Driver> driverList  = driverRepository2.findAll();
		int id  = Integer.MAX_VALUE;
		Driver RequiredDriver  = null;

		for(Driver d : driverList){
			int driverId  = d.getDriverId();
			Cab cab  = d.getCab();
			if(driverId<id && cab.isAvailable()==true){
				RequiredDriver  = d;
				id  = driverId;
			}
		}

		if(id==Integer.MAX_VALUE){
			throw new Exception("No cab available!");
		}

		List<TripBooking> list  = RequiredDriver.getTripBookingList();
        Customer customer  = customerRepository2.findById(customerId).get();
		List<TripBooking> list2  = customer.getTripBookingList();

		TripBooking newTrip  = new TripBooking(fromLocation , toLocation , distanceInKm , TripStatus.CONFIRMED);
		newTrip.setCustomer(customerRepository2.findById(customerId).get());
		newTrip.setDriver(RequiredDriver);
		newTrip  =  tripBookingRepository2.save(newTrip);
		list.add(newTrip);
		list2.add(newTrip);

		customer.setTripBookingList(list2);
		RequiredDriver.setTripBookingList(list);

		customerRepository2.save(customer);
		driverRepository2.save(RequiredDriver);

		return newTrip;
	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
         TripBooking trip1  = tripBookingRepository2.findById(tripId).get();
		 TripBooking trip = tripBookingRepository2.findById(tripId).get();
		 trip.setStatus(TripStatus.CANCELED);

		 Customer customer  = trip.getCustomer();
		 Driver driver  = trip.getDriver();
		 List<TripBooking> customerTripList  = customer.getTripBookingList();
		 List<TripBooking> driverTripList  = driver.getTripBookingList();

		 customerTripList.add(trip);
		 driverTripList.add(trip);

		 customerTripList.remove(trip1);
		 driverTripList.remove(trip1);

		 customer.setTripBookingList(customerTripList);
		 driver.setTripBookingList(driverTripList);


	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
       TripBooking oldTrip  = tripBookingRepository2.findById(tripId).get();
	   TripBooking trip  = tripBookingRepository2.findById(tripId).get();
	   trip.setStatus(TripStatus.COMPLETED);

		Customer customer  = trip.getCustomer();
		Driver driver  = trip.getDriver();
		List<TripBooking> customerTripList  = customer.getTripBookingList();
		List<TripBooking> driverTripList  = driver.getTripBookingList();

		customerTripList.add(trip);
		driverTripList.add(trip);

		customerTripList.remove(oldTrip);
		driverTripList.remove(oldTrip);

		customer.setTripBookingList(customerTripList);
		driver.setTripBookingList(driverTripList);
	}
}
