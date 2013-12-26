package org.preznicek.vehiclesregistration.database.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.preznicek.vehiclesregistration.database.PageData;
import org.preznicek.vehiclesregistration.database.domain.Owner;
import org.preznicek.vehiclesregistration.database.domain.codetable.FuelCT;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Car;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Vehicle;
import org.preznicek.vehiclesregistration.database.service.VehicleService;
import org.preznicek.vehiclesregistration.model.formbean.SearchFormBean;
import org.preznicek.vehiclesregistration.utils.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see http://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/testing.html
 */
@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class VehicleTest {
	
	@Autowired
	private VehicleService vehicleService;
	
	@Before
	public void beforeEachTestMethod() {
//		System.out.println("test in progress");
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpsert() {
		Car car = new Car();
		car.setPlateNumber("1A1 11-11");
		car.setOtherBrandName("Mercedes");
		car.setModel("C500");
		car.setMakingYear(Integer.valueOf(2000));
		car.setMotEnd(new Date());
		car.setWeight(Integer.valueOf(1000));
		car.setVehicleType(VehicleType.CAR.toString());
		car.setFuel(new FuelCT(Integer.valueOf(3001), "", ""));
		car.setSittingPlacesCount(Integer.valueOf(5));
		car.setVolume(Integer.valueOf(1200));
		
		Owner owner = new Owner();
		owner.setFirstname("firstname");
		owner.setLastname("lastname");
		owner.setBirthCertificateNumber("123456/7890");
		owner.setAddress("address");
		owner.setPhone1("123 456 789");
		car.setOwner(owner);
		
		vehicleService.upsert(car);
		Assert.assertNotNull(car.getId());
	}
	
	@Test
	@Transactional
	public void testGetVehicleList() throws ParseException {
		PageData pageData = vehicleService.getVehicleList(new SearchFormBean(), 1);
		List<Vehicle> vehicleList = (List<Vehicle>) pageData.getItems();
		Assert.assertNotNull(vehicleList);
		for (Vehicle vehicle : vehicleList) {
			Assert.assertNotNull(vehicle);
		}
	}
	
	@Test
	@Transactional
	public void testGetVehicleById() {
		Long idCar = Long.valueOf(1);
		
		Car car = (Car) vehicleService.getVehicleById(idCar);
		Assert.assertNotNull(car);
	}
}
