package com.driver.services.impl;

import com.driver.model.Cab;
import com.driver.repository.CabRepository;
import com.driver.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Driver;
import com.driver.repository.DriverRepository;

import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	DriverRepository driverRepository3;

	@Autowired
	CabRepository cabRepository3;

	@Override
	public void register(String mobile, String password){
		//Save a driver in the database having given details and a cab with ratePerKm as 10 and availability as True by default.
        Driver newDriver = new Driver(mobile , password);
		Cab newCab  = new Cab(10 , true);
		newCab  = cabRepository3.save(newCab);

		newDriver.setCab(newCab);
        driverRepository3.save(newDriver);
	}

	@Override
	public void removeDriver(int driverId){
		// Delete driver without using deleteById function
		Optional<Driver> driver  = driverRepository3.findById(driverId);
		if(driver.isEmpty()==true) return;

		Driver driver1  = driver.get();

		driverRepository3.delete(driver1);
	}

	@Override
	public void updateStatus(int driverId){
		//Set the status of respective car to unavailable
        Optional<Driver> optionalDriver  = driverRepository3.findById(driverId);
		if(optionalDriver.isEmpty()) return;

		Driver driver  = optionalDriver.get();
		Cab cab  = driver.getCab();
		cab.setAvailable(Boolean.FALSE);
	}
}
