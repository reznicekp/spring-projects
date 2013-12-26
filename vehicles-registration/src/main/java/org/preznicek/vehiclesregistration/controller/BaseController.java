package org.preznicek.vehiclesregistration.controller;

import java.text.SimpleDateFormat;

import org.preznicek.vehiclesregistration.database.domain.codetable.BodyworkBusCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BodyworkTruckCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BrandBusCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BrandCarCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BrandMotorcycleCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BrandTruckCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.FuelCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.InsuranceCompanyCT;
import org.preznicek.vehiclesregistration.database.service.CodeTableService;
import org.preznicek.vehiclesregistration.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {

	protected SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
	protected SimpleDateFormat motDateFormat = new SimpleDateFormat(Constants.MOT_DATE_FORMAT);
	
	@Autowired
	protected CodeTableService codeTableService;
	
	/**
	 * Metoda se vykonava pred kazdym vykreslenim view (anotace @ModelAttribute). Do modelu prida data 
	 * z ciselniku, takze jsou vzdy dostupna v JSP - napr. pri plneni comboboxu.
	 * @param model
	 */
	@ModelAttribute
	public void fillCodeTables(Model model) {
		if (codeTableService != null) {
			model.addAttribute("brandCar", codeTableService.getCodeTableData(BrandCarCT.class));
			model.addAttribute("brandMotorcycle", codeTableService.getCodeTableData(BrandMotorcycleCT.class));
			model.addAttribute("brandTruck", codeTableService.getCodeTableData(BrandTruckCT.class));
			model.addAttribute("brandBus", codeTableService.getCodeTableData(BrandBusCT.class));
			model.addAttribute("fuel", codeTableService.getCodeTableData(FuelCT.class));
			model.addAttribute("bodyworkBus", codeTableService.getCodeTableData(BodyworkBusCT.class));
			model.addAttribute("bodyworkTruck", codeTableService.getCodeTableData(BodyworkTruckCT.class));
			model.addAttribute("company", codeTableService.getCodeTableData(InsuranceCompanyCT.class));
		}
	}
}
