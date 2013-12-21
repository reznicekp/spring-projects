package org.preznicek.vehiclesregistration.database.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.preznicek.vehiclesregistration.database.domain.Owner;
import org.preznicek.vehiclesregistration.database.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OwnerTest {
	
	@Autowired
	private OwnerService ownerService;
	
	@Before
	public void beforeEachTestMethod() {
//		System.out.println("test in progress");
	}
	
	@Test
	@Transactional
	public void testGetOwnerByBCN() {
		String bcn = "840506/4321";
		
		Owner owner = ownerService.getOwnerByBCN(bcn);
		Assert.assertNotNull(owner);
	}
}
