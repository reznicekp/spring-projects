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
import org.preznicek.vehiclesregistration.database.domain.codetable.ModelCT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class CodeTableController extends BaseController {

	@Autowired
	private WebApplicationContext ctx;
	
	/**
	 * Naplni ciselniky.
	 * @return	Nazev view - presmerovani na uvodni obrazovku aplikace.
	 */
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
		
		int iterator = 5001;
		List<ModelCT> modelCT = new ArrayList<ModelCT>();
		// Audi
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2101), "A1", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2101), "A2", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2101), "A3", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2101), "A4", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2101), "A5", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2101), "A6", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2101), "A7", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2101), "A8", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2101), "Q3", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2101), "Q5", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2101), "Q7", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2101), "TT", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2101), "R8", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2101), "RS", ""));
		// BMW
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2102), "1", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2102), "2", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2102), "3", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2102), "4", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2102), "5", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2102), "6", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2102), "7", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2102), "X", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2102), "Z4", ""));
		// Citroen
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2103), "C1", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2103), "C3", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2103), "C4", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2103), "C5", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2103), "C8", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2103), "Berlingo", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2103), "Jumpy", ""));
		// Hyundai
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2104), "i10", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2104), "i20", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2104), "i30", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2104), "i40", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2104), "ix35", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2104), "Elantra", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2104), "Santa Fe", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2104), "Veloster", ""));
		// Kia
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2105), "Picanto", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2105), "Rio", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2105), "Venga", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2105), "Ceed", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2105), "Carens", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2105), "Sorento", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2105), "Optima", ""));
		// Mazda
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2106), "2", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2106), "3", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2106), "5", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2106), "6", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2106), "MX-5", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2106), "CX-5", ""));
		// Mercedes
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2107), "A", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2107), "B", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2107), "C", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2107), "CL", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2107), "CLA", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2107), "CLS", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2107), "E", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2107), "G", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2107), "GL", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2107), "GLA", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2107), "GLK", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2107), "M", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2107), "S", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2107), "SL", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2107), "SLK", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2107), "SLS AMG", ""));
		// Opel
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2108), "ADAM", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2108), "Cascada", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2108), "Corsa", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2108), "Meriva", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2108), "Mokka", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2108), "Astra", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2108), "Zafira", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2108), "Insignia", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2108), "Antara", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2108), "Vivaro", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2108), "Movano", ""));
		// Peugeot
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2109), "107", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2109), "207", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2109), "208", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2109), "301", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2109), "308", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2109), "508", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2109), "807", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2109), "Bipper", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2109), "Boxer", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2109), "Partner", ""));
		// Renault
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2110), "Twingo", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2110), "Clio", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2110), "Captur", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2110), "Megane", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2110), "Fluence", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2110), "Scenic", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2110), "Koleos", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2110), "Laguna", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2110), "Escape", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2110), "Kangoo", ""));
		// Skoda
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2111), "Fabia", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2111), "Octavia", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2111), "Rapid", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2111), "Superb", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2111), "Yeti", ""));
		// Volkswagen
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2112), "Up", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2112), "Polo", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2112), "Golf", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2112), "Beetle", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2112), "Touran", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2112), "Passat", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2112), "CC", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2112), "Sharan", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2112), "Touareg", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2112), "Phaeton", ""));
		// BMW - moto
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2201), "S", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2201), "R", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2201), "RS", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2201), "RT", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2201), "LT", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2201), "GT", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2201), "GS", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2201), "ST", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2201), "CS", ""));
		// Honda - moto
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2202), "CBR1000RR", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2202), "CBR650F", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2202), "CBR300R", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2202), "CBR500R", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2202), "CBR125R", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2202), "CB1100", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2202), "CB1100EX", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2202), "CB1000R", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2202), "MSX125", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2202), "VT1300CX", ""));
		// Suzuki - moto
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2203), "Hayabusa", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2203), "GSX-R1000", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2203), "GSX-R750", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2203), "GSX-R600", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2203), "Bandit 1250SA", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2203), "Bandit 650FA", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2203), "Bandit 650F", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2203), "Intruder M1800R", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2203), "DR125SM", ""));
		// Yamaha
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2204), "FJR1300A", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2204), "FJR1300AS", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2204), "YZF-R125", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2204), "YZF-R1", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2204), "YZF-R6", ""));
		// Karosa
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2301), "B 732", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2301), "C 954", ""));
		// Mercedes - bus
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2302), "Sprinter", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2302), "Viano", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2302), "Citaro", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2302), "Travego", ""));
		// Renault - bus
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2303), "Trafic", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2303), "Master", ""));
		// Setra
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2304), "S 412 UL", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2304), "S 415 UL", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2304), "S 416 UL", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2304), "S 417 UL", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2304), "S 419 UL", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2304), "S 415 H", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2304), "S 416 H", ""));
		// Avia
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2401), "D75", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2401), "D90", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2401), "D120", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2401), "D120 4x4", ""));
		// Iveco
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2402), "Eurocargo", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2402), "Stralis", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2402), "Daily 4x4", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2402), "Trukker", ""));
		// Mercedes - truck
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2403), "Actros", ""));
		// Renault - truck
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2404), "T440", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2404), "T520", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2404), "T380", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2404), "K380", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2404), "K520", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2404), "C250", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2404), "C380", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2404), "C520", ""));
		// Tatra
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2405), "Phoenix", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2405), "Terrn", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2405), "Jamal", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2405), "T 810", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2405), "T 810 C", ""));
		modelCT.add(new ModelCT(Integer.valueOf(iterator++), Integer.valueOf(2405), "Force", ""));
		codeTableService.fillCodeTable(modelCT);
		
		return "redirect:/search";
	}
	
	/**
	 * Ajaxove volani pri zmene vybrane znacky. Z databaze se dotahnou vsechny modely dane znacky, 
	 * provede se serializace do HTML5 formatu a takto reprezentovany seznam modelu se vrati k vykresleni.<br>
	 * Anotace <code>@ResponseBody</code> je zde uvedena, aby se neprovadelo mapovani vraceneho 
	 * retezce na JSP, ale aby se tento retezec vratil jako response obsluznemu javascriptu.
	 * @param brandCode		Kod znacky.
	 * @return				Seznam modelu v HTML5 formatu.
	 */
	@RequestMapping(value="/get-models", method=RequestMethod.GET)
	@ResponseBody
	public String getModels(@RequestParam(value="brandCode") String brandCode) {
		Integer code = Integer.valueOf(brandCode);
		List<ModelCT> modelList = codeTableService.getModelListByBrand(code);
		
		String options = "";
		for (ModelCT model : modelList) {
			options += "<option value=\"" + model.getValue() + "\">";
		}
		
		return options;
	}
}
