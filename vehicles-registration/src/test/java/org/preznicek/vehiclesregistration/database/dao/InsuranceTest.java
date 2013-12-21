package org.preznicek.vehiclesregistration.database.dao;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.preznicek.vehiclesregistration.database.domain.Insurance;
import org.preznicek.vehiclesregistration.database.domain.codetable.InsuranceCompanyCT;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Vehicle;
import org.preznicek.vehiclesregistration.database.service.InsuranceService;
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
public class InsuranceTest {
	
	@Autowired
	private InsuranceService insuranceService;
	
	@Before
	public void beforeEachTestMethod() {
//		System.out.println("test in progress");
	}
	
	@Test
	@Transactional
	public void testGetInsuranceListByCar() {
		Long idCar = Long.valueOf(1);
		
		List<Insurance> insuranceList = insuranceService.getInsuranceListByCar(idCar);
		Assert.assertNotNull(insuranceList);
		for (Insurance insurance : insuranceList) {
			Assert.assertNotNull(insurance);
		}
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpsert() {
		Insurance insurance = new Insurance();
		insurance.setInsuranceCompany(new InsuranceCompanyCT(Integer.valueOf(6001), "", ""));
		insurance.setOtherInsuranceCompanyName("");
		insurance.setActiveFrom(new Date());
		insurance.setActiveTo(new Date());
		
		Vehicle vehicle = new Vehicle();
		vehicle.setId(Long.valueOf(1));
		insurance.setVehicle(vehicle);
		
		insuranceService.upsert(insurance);
		Assert.assertNotNull(insurance.getId());
	}
	
	@Test
	@Transactional
	public void testGetInsuranceById() {
		Long id = Long.valueOf(1);
		
		Insurance insurance = insuranceService.getInsuranceById(id);
		Assert.assertNotNull(insurance);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete() {
		Long id = Long.valueOf(1);
		Insurance insurance = insuranceService.getInsuranceById(id);
		
		insuranceService.delete(insurance);
		
		insurance = insuranceService.getInsuranceById(id);
		Assert.assertNull(insurance);
	}
}
