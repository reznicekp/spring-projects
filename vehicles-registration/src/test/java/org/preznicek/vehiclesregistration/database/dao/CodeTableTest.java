package org.preznicek.vehiclesregistration.database.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.preznicek.vehiclesregistration.database.domain.codetable.BodyworkBusCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BodyworkTruckCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BrandBusCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BrandCarCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BrandMotorcycleCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BrandTruckCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.CodeTable;
import org.preznicek.vehiclesregistration.database.domain.codetable.FuelCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.InsuranceCompanyCT;
import org.preznicek.vehiclesregistration.database.service.CodeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see http://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/testing.html
 */
@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration	// pokud neni v parametru dany nazev konfiguracnich souboru, bere se soubor se stejnym nazvem a suffixem "-context" ve stejne slozce jako je java soubor
@WebAppConfiguration
public class CodeTableTest {
	
	@Autowired
	private CodeTableService codeTableService;
	
	@Before
	public void beforeEachTestMethod() {
//		System.out.println("test in progress");
	}
	
	@Test
	@Transactional
	public void testBodyworkBusCT() {
		List<? extends CodeTable> codeTableData = codeTableService.getCodeTableData(BodyworkBusCT.class);
		testCodeTable(codeTableData);
	}
	
	@Test
	@Transactional
	public void testBodyworkTruckCT() {
		List<? extends CodeTable> codeTableData = codeTableService.getCodeTableData(BodyworkTruckCT.class);
		testCodeTable(codeTableData);
	}
	
	@Test
	@Transactional
	public void testBrandCarCT() {
		List<? extends CodeTable> codeTableData = codeTableService.getCodeTableData(BrandCarCT.class);
		testCodeTable(codeTableData);
	}
	
	@Test
	@Transactional
	public void testBrandMotorcycleCT() {
		List<? extends CodeTable> codeTableData = codeTableService.getCodeTableData(BrandMotorcycleCT.class);
		testCodeTable(codeTableData);
	}
	
	@Test
	@Transactional
	public void testBrandBusCT() {
		List<? extends CodeTable> codeTableData = codeTableService.getCodeTableData(BrandBusCT.class);
		testCodeTable(codeTableData);
	}
	
	@Test
	@Transactional
	public void testBrandTruckCT() {
		List<? extends CodeTable> codeTableData = codeTableService.getCodeTableData(BrandTruckCT.class);
		testCodeTable(codeTableData);
	}
	
	@Test
	@Transactional
	public void testFuelCT() {
		List<? extends CodeTable> codeTableData = codeTableService.getCodeTableData(FuelCT.class);
		testCodeTable(codeTableData);
	}
	
	@Test
	@Transactional
	public void testInsuranceCompanyCT() {
		List<? extends CodeTable> codeTableData = codeTableService.getCodeTableData(InsuranceCompanyCT.class);
		testCodeTable(codeTableData);
	}
	
	private void testCodeTable(List<? extends CodeTable> codeTableData) {
		Assert.assertNotNull(codeTableData);
		Assert.assertNotEquals("No data in code table", codeTableData.size(), 0);
		for (CodeTable codeTableRow : codeTableData) {
			Assert.assertNotNull(codeTableRow);
		}
	}
}
