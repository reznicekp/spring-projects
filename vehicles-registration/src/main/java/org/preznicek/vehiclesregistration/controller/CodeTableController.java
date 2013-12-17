package org.preznicek.vehiclesregistration.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.preznicek.vehiclesregistration.database.domain.codetable.BodyworkBusCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BodyworkTruckCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BrandCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.FuelCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.InsuranceCompanyCT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class CodeTableController extends BaseController {

	@Autowired
	private WebApplicationContext ctx;
	
	@RequestMapping(value="/ct")
	public String fillCodeTables() {
		List<BodyworkBusCT> bodyworkBusCT = new ArrayList<BodyworkBusCT>();
		bodyworkBusCT.add(new BodyworkBusCT(Integer.valueOf(1001), ctx.getMessage("bodywork_municipal", null, Locale.getDefault()), "mestsky"));
		bodyworkBusCT.add(new BodyworkBusCT(Integer.valueOf(1002), ctx.getMessage("bodywork_suburban", null, Locale.getDefault()), "primestsky"));
		bodyworkBusCT.add(new BodyworkBusCT(Integer.valueOf(1003), ctx.getMessage("bodywork_long_distance", null, Locale.getDefault()), "dalkovy"));
		bodyworkBusCT.add(new BodyworkBusCT(Integer.valueOf(1004), ctx.getMessage("bodywork_living", null, Locale.getDefault()), "obytny"));
		codeTableService.fillCodeTable(bodyworkBusCT);
		
		List<BodyworkTruckCT> bodyworkTruckCT = new ArrayList<BodyworkTruckCT>();
		bodyworkTruckCT.add(new BodyworkTruckCT(Integer.valueOf(3001), ctx.getMessage("bodywork_trailer", null, Locale.getDefault()), "tahac"));
		bodyworkTruckCT.add(new BodyworkTruckCT(Integer.valueOf(3002), ctx.getMessage("bodywork_flatbed", null, Locale.getDefault()), "valnik"));
		bodyworkTruckCT.add(new BodyworkTruckCT(Integer.valueOf(3003), ctx.getMessage("bodywork_tipper", null, Locale.getDefault()), "sklapec"));
		bodyworkTruckCT.add(new BodyworkTruckCT(Integer.valueOf(3004), ctx.getMessage("bodywork_case", null, Locale.getDefault()), "skrin"));
		bodyworkTruckCT.add(new BodyworkTruckCT(Integer.valueOf(3005), ctx.getMessage("bodywork_izotherm", null, Locale.getDefault()), "mrazici"));
		bodyworkTruckCT.add(new BodyworkTruckCT(Integer.valueOf(3006), ctx.getMessage("bodywork_tanker", null, Locale.getDefault()), "cisterna"));
		bodyworkTruckCT.add(new BodyworkTruckCT(Integer.valueOf(3007), ctx.getMessage("bodywork_special", null, Locale.getDefault()), "specialni"));
		codeTableService.fillCodeTable(bodyworkTruckCT);
		
		List<BrandCT> brandCT = new ArrayList<BrandCT>();
		brandCT.add(new BrandCT(Integer.valueOf(4001), ctx.getMessage("brand_audi", null, Locale.getDefault()), ""));
		brandCT.add(new BrandCT(Integer.valueOf(4002), ctx.getMessage("brand_bmw", null, Locale.getDefault()), ""));
		brandCT.add(new BrandCT(Integer.valueOf(4003), ctx.getMessage("brand_citroen", null, Locale.getDefault()), ""));
		brandCT.add(new BrandCT(Integer.valueOf(4004), ctx.getMessage("brand_hyundai", null, Locale.getDefault()), ""));
		brandCT.add(new BrandCT(Integer.valueOf(4005), ctx.getMessage("brand_kia", null, Locale.getDefault()), ""));
		brandCT.add(new BrandCT(Integer.valueOf(4006), ctx.getMessage("brand_mazda", null, Locale.getDefault()), ""));
		brandCT.add(new BrandCT(Integer.valueOf(4007), ctx.getMessage("brand_opel", null, Locale.getDefault()), ""));
		brandCT.add(new BrandCT(Integer.valueOf(4008), ctx.getMessage("brand_peugeot", null, Locale.getDefault()), ""));
		brandCT.add(new BrandCT(Integer.valueOf(4009), ctx.getMessage("brand_renault", null, Locale.getDefault()), ""));
		brandCT.add(new BrandCT(Integer.valueOf(4010), ctx.getMessage("brand_skoda", null, Locale.getDefault()), ""));
		brandCT.add(new BrandCT(Integer.valueOf(4011), ctx.getMessage("brand_volkswagen", null, Locale.getDefault()), ""));
		codeTableService.fillCodeTable(brandCT);
		
		List<FuelCT> fuelCT = new ArrayList<FuelCT>();
		fuelCT.add(new FuelCT(Integer.valueOf(5001), ctx.getMessage("fuel_petrol", null, Locale.getDefault()), "benzin"));
		fuelCT.add(new FuelCT(Integer.valueOf(5002), ctx.getMessage("fuel_diesel", null, Locale.getDefault()), "nafta"));
		fuelCT.add(new FuelCT(Integer.valueOf(5003), ctx.getMessage("fuel_cng", null, Locale.getDefault()), ""));
		fuelCT.add(new FuelCT(Integer.valueOf(5004), ctx.getMessage("fuel_electric", null, Locale.getDefault()), ""));
		fuelCT.add(new FuelCT(Integer.valueOf(5005), ctx.getMessage("fuel_hybrid", null, Locale.getDefault()), ""));
		codeTableService.fillCodeTable(fuelCT);
		
		List<InsuranceCompanyCT> insuranceCompanyCT = new ArrayList<InsuranceCompanyCT>();
		insuranceCompanyCT.add(new InsuranceCompanyCT(Integer.valueOf(6001), ctx.getMessage("company_allianz", null, Locale.getDefault()), ""));
		insuranceCompanyCT.add(new InsuranceCompanyCT(Integer.valueOf(6002), ctx.getMessage("company_axa", null, Locale.getDefault()), ""));
		insuranceCompanyCT.add(new InsuranceCompanyCT(Integer.valueOf(6003), ctx.getMessage("company_cp", null, Locale.getDefault()), ""));
		insuranceCompanyCT.add(new InsuranceCompanyCT(Integer.valueOf(6004), ctx.getMessage("company_csob", null, Locale.getDefault()), ""));
		insuranceCompanyCT.add(new InsuranceCompanyCT(Integer.valueOf(6005), ctx.getMessage("company_kooperativa", null, Locale.getDefault()), ""));
		insuranceCompanyCT.add(new InsuranceCompanyCT(Integer.valueOf(6006), ctx.getMessage("company_slavia", null, Locale.getDefault()), ""));
		insuranceCompanyCT.add(new InsuranceCompanyCT(Integer.valueOf(6007), ctx.getMessage("company_uniqa", null, Locale.getDefault()), ""));
		insuranceCompanyCT.add(new InsuranceCompanyCT(Integer.valueOf(6008), ctx.getMessage("company_wustenrot", null, Locale.getDefault()), ""));
		codeTableService.fillCodeTable(insuranceCompanyCT);
		
		return "redirect:/search";
	}
}
