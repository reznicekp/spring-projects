package org.preznicek.vehiclesregistration.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.preznicek.vehiclesregistration.database.domain.codetable.BodyworkBusCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BodyworkTruckCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BrandBusCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BrandCarCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BrandMotorcycleCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BrandTruckCT;
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
		bodyworkBusCT.add(new BodyworkBusCT(Integer.valueOf(1101), ctx.getMessage("bodywork_municipal", null, Locale.getDefault()), "mestsky"));
		bodyworkBusCT.add(new BodyworkBusCT(Integer.valueOf(1102), ctx.getMessage("bodywork_suburban", null, Locale.getDefault()), "primestsky"));
		bodyworkBusCT.add(new BodyworkBusCT(Integer.valueOf(1103), ctx.getMessage("bodywork_long_distance", null, Locale.getDefault()), "dalkovy"));
		bodyworkBusCT.add(new BodyworkBusCT(Integer.valueOf(1104), ctx.getMessage("bodywork_living", null, Locale.getDefault()), "obytny"));
		codeTableService.fillCodeTable(bodyworkBusCT);
		
		List<BodyworkTruckCT> bodyworkTruckCT = new ArrayList<BodyworkTruckCT>();
		bodyworkTruckCT.add(new BodyworkTruckCT(Integer.valueOf(1201), ctx.getMessage("bodywork_trailer", null, Locale.getDefault()), "tahac"));
		bodyworkTruckCT.add(new BodyworkTruckCT(Integer.valueOf(1202), ctx.getMessage("bodywork_flatbed", null, Locale.getDefault()), "valnik"));
		bodyworkTruckCT.add(new BodyworkTruckCT(Integer.valueOf(1203), ctx.getMessage("bodywork_tipper", null, Locale.getDefault()), "sklapec"));
		bodyworkTruckCT.add(new BodyworkTruckCT(Integer.valueOf(1204), ctx.getMessage("bodywork_case", null, Locale.getDefault()), "skrin"));
		bodyworkTruckCT.add(new BodyworkTruckCT(Integer.valueOf(1205), ctx.getMessage("bodywork_izotherm", null, Locale.getDefault()), "mrazici"));
		bodyworkTruckCT.add(new BodyworkTruckCT(Integer.valueOf(1206), ctx.getMessage("bodywork_tanker", null, Locale.getDefault()), "cisterna"));
		bodyworkTruckCT.add(new BodyworkTruckCT(Integer.valueOf(1207), ctx.getMessage("bodywork_special", null, Locale.getDefault()), "specialni"));
		codeTableService.fillCodeTable(bodyworkTruckCT);
		
		List<BrandCarCT> brandCarCT = new ArrayList<BrandCarCT>();
		brandCarCT.add(new BrandCarCT(Integer.valueOf(2101), ctx.getMessage("brand_audi", null, Locale.getDefault()), ""));
		brandCarCT.add(new BrandCarCT(Integer.valueOf(2102), ctx.getMessage("brand_bmw", null, Locale.getDefault()), ""));
		brandCarCT.add(new BrandCarCT(Integer.valueOf(2103), ctx.getMessage("brand_citroen", null, Locale.getDefault()), ""));
		brandCarCT.add(new BrandCarCT(Integer.valueOf(2104), ctx.getMessage("brand_hyundai", null, Locale.getDefault()), ""));
		brandCarCT.add(new BrandCarCT(Integer.valueOf(2105), ctx.getMessage("brand_kia", null, Locale.getDefault()), ""));
		brandCarCT.add(new BrandCarCT(Integer.valueOf(2106), ctx.getMessage("brand_mazda", null, Locale.getDefault()), ""));
		brandCarCT.add(new BrandCarCT(Integer.valueOf(2107), ctx.getMessage("brand_mercedes", null, Locale.getDefault()), ""));
		brandCarCT.add(new BrandCarCT(Integer.valueOf(2108), ctx.getMessage("brand_opel", null, Locale.getDefault()), ""));
		brandCarCT.add(new BrandCarCT(Integer.valueOf(2109), ctx.getMessage("brand_peugeot", null, Locale.getDefault()), ""));
		brandCarCT.add(new BrandCarCT(Integer.valueOf(2110), ctx.getMessage("brand_renault", null, Locale.getDefault()), ""));
		brandCarCT.add(new BrandCarCT(Integer.valueOf(2111), ctx.getMessage("brand_skoda", null, Locale.getDefault()), ""));
		brandCarCT.add(new BrandCarCT(Integer.valueOf(2112), ctx.getMessage("brand_volkswagen", null, Locale.getDefault()), ""));
		codeTableService.fillCodeTable(brandCarCT);
		
		List<BrandMotorcycleCT> brandMotorcycleCT = new ArrayList<BrandMotorcycleCT>();
		brandMotorcycleCT.add(new BrandMotorcycleCT(Integer.valueOf(2201), ctx.getMessage("brand_bmw", null, Locale.getDefault()), ""));
		brandMotorcycleCT.add(new BrandMotorcycleCT(Integer.valueOf(2202), ctx.getMessage("brand_honda", null, Locale.getDefault()), ""));
		brandMotorcycleCT.add(new BrandMotorcycleCT(Integer.valueOf(2203), ctx.getMessage("brand_suzuki", null, Locale.getDefault()), ""));
		brandMotorcycleCT.add(new BrandMotorcycleCT(Integer.valueOf(2204), ctx.getMessage("brand_yamaha", null, Locale.getDefault()), ""));
		codeTableService.fillCodeTable(brandMotorcycleCT);
		
		List<BrandBusCT> brandBusCT = new ArrayList<BrandBusCT>();
		brandBusCT.add(new BrandBusCT(Integer.valueOf(2301), ctx.getMessage("brand_karosa", null, Locale.getDefault()), ""));
		brandBusCT.add(new BrandBusCT(Integer.valueOf(2302), ctx.getMessage("brand_mercedes", null, Locale.getDefault()), ""));
		brandBusCT.add(new BrandBusCT(Integer.valueOf(2303), ctx.getMessage("brand_renault", null, Locale.getDefault()), ""));
		brandBusCT.add(new BrandBusCT(Integer.valueOf(2304), ctx.getMessage("brand_setra", null, Locale.getDefault()), ""));
		codeTableService.fillCodeTable(brandBusCT);
		
		List<BrandTruckCT> brandTruckCT = new ArrayList<BrandTruckCT>();
		brandTruckCT.add(new BrandTruckCT(Integer.valueOf(2401), ctx.getMessage("brand_avia", null, Locale.getDefault()), ""));
		brandTruckCT.add(new BrandTruckCT(Integer.valueOf(2402), ctx.getMessage("brand_iveco", null, Locale.getDefault()), ""));
		brandTruckCT.add(new BrandTruckCT(Integer.valueOf(2403), ctx.getMessage("brand_mercedes", null, Locale.getDefault()), ""));
		brandTruckCT.add(new BrandTruckCT(Integer.valueOf(2404), ctx.getMessage("brand_renault", null, Locale.getDefault()), ""));
		brandTruckCT.add(new BrandTruckCT(Integer.valueOf(2405), ctx.getMessage("brand_tatra", null, Locale.getDefault()), ""));
		codeTableService.fillCodeTable(brandTruckCT);
		
		List<FuelCT> fuelCT = new ArrayList<FuelCT>();
		fuelCT.add(new FuelCT(Integer.valueOf(3001), ctx.getMessage("fuel_petrol", null, Locale.getDefault()), "benzin"));
		fuelCT.add(new FuelCT(Integer.valueOf(3002), ctx.getMessage("fuel_diesel", null, Locale.getDefault()), "nafta"));
		fuelCT.add(new FuelCT(Integer.valueOf(3003), ctx.getMessage("fuel_cng", null, Locale.getDefault()), ""));
		fuelCT.add(new FuelCT(Integer.valueOf(3004), ctx.getMessage("fuel_electric", null, Locale.getDefault()), ""));
		fuelCT.add(new FuelCT(Integer.valueOf(3005), ctx.getMessage("fuel_hybrid", null, Locale.getDefault()), ""));
		codeTableService.fillCodeTable(fuelCT);
		
		List<InsuranceCompanyCT> insuranceCompanyCT = new ArrayList<InsuranceCompanyCT>();
		insuranceCompanyCT.add(new InsuranceCompanyCT(Integer.valueOf(4001), ctx.getMessage("company_allianz", null, Locale.getDefault()), ""));
		insuranceCompanyCT.add(new InsuranceCompanyCT(Integer.valueOf(4002), ctx.getMessage("company_axa", null, Locale.getDefault()), ""));
		insuranceCompanyCT.add(new InsuranceCompanyCT(Integer.valueOf(4003), ctx.getMessage("company_cp", null, Locale.getDefault()), ""));
		insuranceCompanyCT.add(new InsuranceCompanyCT(Integer.valueOf(4004), ctx.getMessage("company_csob", null, Locale.getDefault()), ""));
		insuranceCompanyCT.add(new InsuranceCompanyCT(Integer.valueOf(4005), ctx.getMessage("company_kooperativa", null, Locale.getDefault()), ""));
		insuranceCompanyCT.add(new InsuranceCompanyCT(Integer.valueOf(4006), ctx.getMessage("company_slavia", null, Locale.getDefault()), ""));
		insuranceCompanyCT.add(new InsuranceCompanyCT(Integer.valueOf(4007), ctx.getMessage("company_uniqa", null, Locale.getDefault()), ""));
		insuranceCompanyCT.add(new InsuranceCompanyCT(Integer.valueOf(4008), ctx.getMessage("company_wustenrot", null, Locale.getDefault()), ""));
		codeTableService.fillCodeTable(insuranceCompanyCT);
		
		return "redirect:/search";
	}
}
