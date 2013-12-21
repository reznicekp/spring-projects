package org.preznicek.vehiclesregistration.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.preznicek.vehiclesregistration.database.domain.Insurance;
import org.preznicek.vehiclesregistration.database.domain.codetable.InsuranceCompanyCT;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Bus;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Car;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Motorcycle;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Truck;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Vehicle;
import org.preznicek.vehiclesregistration.database.service.InsuranceService;
import org.preznicek.vehiclesregistration.database.service.VehicleService;
import org.preznicek.vehiclesregistration.model.formbean.CreateBusFormBean;
import org.preznicek.vehiclesregistration.model.formbean.CreateCarFormBean;
import org.preznicek.vehiclesregistration.model.formbean.CreateFormBean;
import org.preznicek.vehiclesregistration.model.formbean.CreateMotorcycleFormBean;
import org.preznicek.vehiclesregistration.model.formbean.CreateTruckFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class InsuranceController extends BaseController {

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private InsuranceService insuranceService;
	
	@RequestMapping(value="/upsert-car", params="saveInsurance", method=RequestMethod.POST)
	public ModelAndView createCarInsurance(@Valid @ModelAttribute(value="createFormBean") CreateCarFormBean createFormBean, BindingResult result) throws ParseException {
		if (result.hasErrors()) {
			return new ModelAndView("createCarTile");
		}
		
		Vehicle vehicle = vehicleService.getVehicleById(Long.valueOf(createFormBean.getVehicle().getId()), Car.class);
		
		insuranceService.upsert(setInsuranceAttributes(vehicle, createFormBean));
		
		return new ModelAndView(new RedirectView("/detail-car/" + createFormBean.getVehicle().getId(), true));
	}
	
	@RequestMapping(value="/upsert-motorcycle", params="saveInsurance", method=RequestMethod.POST)
	public ModelAndView createMotorcycleInsurance(@Valid @ModelAttribute(value="createFormBean") CreateMotorcycleFormBean createFormBean, BindingResult result) throws ParseException {
		if (result.hasErrors()) {
			return new ModelAndView("createMotorcycleTile");
		}
		
		Vehicle vehicle = vehicleService.getVehicleById(Long.valueOf(createFormBean.getVehicle().getId()), Motorcycle.class);
		
		insuranceService.upsert(setInsuranceAttributes(vehicle, createFormBean));
		
		return new ModelAndView(new RedirectView("/detail-motorcycle/" + createFormBean.getVehicle().getId(), true));
	}
	
	@RequestMapping(value="/upsert-truck", params="saveInsurance", method=RequestMethod.POST)
	public ModelAndView createTruckInsurance(@Valid @ModelAttribute(value="createFormBean") CreateTruckFormBean createFormBean, BindingResult result) throws ParseException {
		if (result.hasErrors()) {
			return new ModelAndView("createTruckTile");
		}
		
		Vehicle vehicle = vehicleService.getVehicleById(Long.valueOf(createFormBean.getVehicle().getId()), Truck.class);
		
		insuranceService.upsert(setInsuranceAttributes(vehicle, createFormBean));
		
		return new ModelAndView(new RedirectView("/detail-truck/" + createFormBean.getVehicle().getId(), true));
	}
	
	@RequestMapping(value="/upsert-bus", params="saveInsurance", method=RequestMethod.POST)
	public ModelAndView createBusInsurance(@Valid @ModelAttribute(value="createFormBean") CreateBusFormBean createFormBean, BindingResult result) throws ParseException {
		if (result.hasErrors()) {
			return new ModelAndView("createBusTile");
		}
		
		Vehicle vehicle = vehicleService.getVehicleById(Long.valueOf(createFormBean.getVehicle().getId()), Bus.class);
		
		insuranceService.upsert(setInsuranceAttributes(vehicle, createFormBean));
		
		return new ModelAndView(new RedirectView("/detail-bus/" + createFormBean.getVehicle().getId(), true));
	}
	
	private Insurance setInsuranceAttributes(Vehicle vehicle, CreateFormBean createFormBean) throws ParseException {
		Insurance insurance = new Insurance();
		insurance.setInsuranceCompany((InsuranceCompanyCT) codeTableService.getCodeTableRow(InsuranceCompanyCT.class, Integer.valueOf(createFormBean.getInsurance().getCompanyCode())));
		insurance.setOtherInsuranceCompanyName(createFormBean.getInsurance().getOtherCompanyName());
		insurance.setActiveFrom(dateFormat.parse(createFormBean.getInsurance().getActiveFrom()));
		insurance.setActiveTo(dateFormat.parse(createFormBean.getInsurance().getActiveTo()));
		insurance.setVehicle(vehicle);
		
		return insurance;
	}
	
	@RequestMapping(value="/delete-insurance/{id}", method=RequestMethod.GET)
	public String deleteInsurance(@PathVariable(value="id") Long id) {
		Insurance insurance = insuranceService.getInsuranceById(id);
		
		String vehicleType = insurance.getVehicle().getVehicleType();
		String vehicleId = String.valueOf(insurance.getVehicle().getId());
		
		insuranceService.delete(insurance);
		
		return "redirect:/detail-" + vehicleType.toLowerCase() + "/" + vehicleId;
	}
}
