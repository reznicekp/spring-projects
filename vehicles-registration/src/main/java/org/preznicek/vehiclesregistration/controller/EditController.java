package org.preznicek.vehiclesregistration.controller;

import java.util.ArrayList;
import java.util.List;

import org.preznicek.vehiclesregistration.database.domain.Insurance;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Bus;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Car;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Motorcycle;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Truck;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Vehicle;
import org.preznicek.vehiclesregistration.database.service.VehicleService;
import org.preznicek.vehiclesregistration.model.formbean.BusFormBean;
import org.preznicek.vehiclesregistration.model.formbean.CarFormBean;
import org.preznicek.vehiclesregistration.model.formbean.CreateBusFormBean;
import org.preznicek.vehiclesregistration.model.formbean.CreateCarFormBean;
import org.preznicek.vehiclesregistration.model.formbean.CreateFormBean;
import org.preznicek.vehiclesregistration.model.formbean.CreateMotorcycleFormBean;
import org.preznicek.vehiclesregistration.model.formbean.CreateTruckFormBean;
import org.preznicek.vehiclesregistration.model.formbean.InsuranceFormBean;
import org.preznicek.vehiclesregistration.model.formbean.InsuranceItemInListFormBean;
import org.preznicek.vehiclesregistration.model.formbean.MotorcycleFormBean;
import org.preznicek.vehiclesregistration.model.formbean.OwnerFormBean;
import org.preznicek.vehiclesregistration.model.formbean.SearchResultFormBean;
import org.preznicek.vehiclesregistration.model.formbean.TruckFormBean;
import org.preznicek.vehiclesregistration.model.formbean.VehicleFormBean;
import org.preznicek.vehiclesregistration.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EditController extends BaseController {

	@Autowired
	private VehicleService vehicleService;
	
	/**
	 * Na zaklade ID v <code>detailFormBean</code> z databaze dotahne detail automobilu a tato data 
	 * zobrazi v editovatelnem formulari.
	 * @param detailFormBean	Formular pro upravu udaju vozidla.
	 * @return
	 */
	@RequestMapping(value="/edit-car", method=RequestMethod.GET)
	public ModelAndView showEditCar(@ModelAttribute(value="detailCarFormBean") CreateCarFormBean detailFormBean) {
		Car car = (Car) vehicleService.getVehicleById(Long.valueOf(detailFormBean.getVehicle().getId()), Car.class);
		
		CreateCarFormBean createFormBean = new CreateCarFormBean();
		setGeneralAttributes(car, createFormBean);
		
		CarFormBean carFormBean = new CarFormBean();
		carFormBean.setSittingPlacesCount(String.valueOf(car.getSittingPlacesCount()));
		carFormBean.setVolume(String.valueOf(car.getVolume()));
		carFormBean.setFuel(String.valueOf(car.getFuel().getCode()));
		
		createFormBean.setCar(carFormBean);
		createFormBean.getVehicle().setBrand(car.getBrand() != null ? String.valueOf(car.getBrand().getCode()) : "");
		
		return new ModelAndView("createCarTile", "createFormBean", createFormBean);
	}
	
	/**
	 * Na zaklade ID v <code>detailFormBean</code> z databaze dotahne detail motocyklu a tato data 
	 * zobrazi v editovatelnem formulari.
	 * @param detailFormBean	Formular pro upravu udaju vozidla.
	 * @return
	 */
	@RequestMapping(value="/edit-motorcycle", method=RequestMethod.GET)
	public ModelAndView showEditMotorcycle(@ModelAttribute(value="detailMotorcycleFormBean") CreateMotorcycleFormBean detailFormBean) {
		Motorcycle motorcycle = (Motorcycle) vehicleService.getVehicleById(Long.valueOf(detailFormBean.getVehicle().getId()), Motorcycle.class);
		
		CreateMotorcycleFormBean createFormBean = new CreateMotorcycleFormBean();
		setGeneralAttributes(motorcycle, createFormBean);
		
		MotorcycleFormBean motorcycleFormBean = new MotorcycleFormBean();
		motorcycleFormBean.setVolume(String.valueOf(motorcycle.getVolume()));
		
		createFormBean.setMotorcycle(motorcycleFormBean);
		createFormBean.getVehicle().setBrand(motorcycle.getBrand() != null ? String.valueOf(motorcycle.getBrand().getCode()) : "");
		
		return new ModelAndView("createMotorcycleTile", "createFormBean", createFormBean);
	}
	
	/**
	 * Na zaklade ID v <code>detailFormBean</code> z databaze dotahne detail nakladniho automobilu 
	 * a tato data zobrazi v editovatelnem formulari.
	 * @param detailFormBean	Formular pro upravu udaju vozidla.
	 * @return
	 */
	@RequestMapping(value="/edit-truck", method=RequestMethod.GET)
	public ModelAndView showEditTruck(@ModelAttribute(value="detailTruckFormBean") CreateTruckFormBean detailFormBean) {
		Truck truck = (Truck) vehicleService.getVehicleById(Long.valueOf(detailFormBean.getVehicle().getId()), Truck.class);
		
		CreateTruckFormBean createFormBean = new CreateTruckFormBean();
		setGeneralAttributes(truck, createFormBean);
		
		TruckFormBean truckFormBean = new TruckFormBean();
		truckFormBean.setVolume(String.valueOf(truck.getVolume()));
		truckFormBean.setBodywork(String.valueOf(truck.getBodywork().getCode()));
		
		createFormBean.setTruck(truckFormBean);
		createFormBean.getVehicle().setBrand(truck.getBrand() != null ? String.valueOf(truck.getBrand().getCode()) : "");
		
		return new ModelAndView("createTruckTile", "createFormBean", createFormBean);
	}
	
	/**
	 * Na zaklade ID v <code>detailFormBean</code> z databaze dotahne detail autobusu a tato data 
	 * zobrazi v editovatelnem formulari.
	 * @param detailFormBean	Formular pro upravu udaju vozidla.
	 * @return
	 */
	@RequestMapping(value="/edit-bus", method=RequestMethod.GET)
	public ModelAndView showEditBus(@ModelAttribute(value="detailBusFormBean") CreateBusFormBean detailFormBean) {
		Bus bus = (Bus) vehicleService.getVehicleById(Long.valueOf(detailFormBean.getVehicle().getId()), Bus.class);
		
		CreateBusFormBean createFormBean = new CreateBusFormBean();
		setGeneralAttributes(bus, createFormBean);
		
		BusFormBean busFormBean = new BusFormBean();
		busFormBean.setBodywork(String.valueOf(bus.getBodywork().getCode()));
		busFormBean.setSittingPlacesCount(String.valueOf(bus.getSittingPlacesCount()));
		busFormBean.setVolume(String.valueOf(bus.getVolume()));
		
		createFormBean.setBus(busFormBean);
		createFormBean.getVehicle().setBrand(bus.getBrand() != null ? String.valueOf(bus.getBrand().getCode()) : "");
		
		return new ModelAndView("createBusTile", "createFormBean", createFormBean);
	}
	
	/**
	 * Na zaklade udaju vozidla vyplni obecne atributy <code>detailFormBean</code> (spolecne pro 
	 * vsechny typy vozidel).
	 * @param vehicle			Vozidlo, jehoz udaje se vyplnuji do <code>detailFormBean</code>.
	 * @param detailFormBean	Editovatelny formular se spolecnymi udaji pro vsechny typy vozidel.
	 */
	private void setGeneralAttributes(Vehicle vehicle, CreateFormBean detailFormBean) {
		VehicleFormBean vehicleFormBean = new VehicleFormBean();
		vehicleFormBean.setId(String.valueOf(vehicle.getId()));
		vehicleFormBean.setPlateNumber(vehicle.getPlateNumber());
		vehicleFormBean.setOtherBrandName(vehicle.getOtherBrandName());
		vehicleFormBean.setModel(vehicle.getModel());
		vehicleFormBean.setMakingYear(String.valueOf(vehicle.getMakingYear()));
		vehicleFormBean.setMotEnd(vehicle.getMotEnd() != null ? motDateFormat.format(vehicle.getMotEnd()) : "");
		vehicleFormBean.setWeight(vehicle.getWeight() != null ? String.valueOf(vehicle.getWeight()) : "");
		
		OwnerFormBean ownerFormBean = new OwnerFormBean();
		ownerFormBean.setId(String.valueOf(vehicle.getOwner().getId()));
		ownerFormBean.setFirstname(vehicle.getOwner().getFirstname());
		ownerFormBean.setLastname(vehicle.getOwner().getLastname());
		ownerFormBean.setBirthCertificateNumber(vehicle.getOwner().getBirthCertificateNumber());
		ownerFormBean.setAddress(vehicle.getOwner().getAddress());
		ownerFormBean.setPhone1(vehicle.getOwner().getPhone1());
		ownerFormBean.setPhone2(vehicle.getOwner().getPhone2());
		
		List<SearchResultFormBean> searchResultList = new ArrayList<SearchResultFormBean>();
		for (Vehicle vehicleOfOwner : vehicle.getOwner().getVehicleList()) {
			if (vehicleOfOwner.getId().longValue() == vehicle.getId().longValue()) {	// aktualne zobrazovane vozidlo se v seznamu nezobrazuje
				continue;
			}
			
			SearchResultFormBean searchResult = new SearchResultFormBean();
			searchResult.setId(String.valueOf(vehicleOfOwner.getId()));
			searchResult.setVehicleType(vehicleOfOwner.getVehicleType());
			searchResult.setPlateNumber(vehicleOfOwner.getPlateNumber());
			searchResult.setBrandAndModel(Helper.getBrandOfVehicle(vehicleOfOwner) + " " + vehicleOfOwner.getModel());
			searchResult.setMakingYear(String.valueOf(vehicleOfOwner.getMakingYear()));
			searchResult.setMotEnd(vehicleOfOwner.getMotEnd() != null ? motDateFormat.format(vehicleOfOwner.getMotEnd()) : "");
			
			searchResultList.add(searchResult);
		}
		ownerFormBean.setVehiclesOfOwner(searchResultList);
		
		List<InsuranceItemInListFormBean> insuranceList = new ArrayList<InsuranceItemInListFormBean>();
		for (Insurance insurance : vehicle.getInsuranceList()) {
			InsuranceItemInListFormBean insuranceItemInListFormBean = new InsuranceItemInListFormBean();
			insuranceItemInListFormBean.setId(String.valueOf(insurance.getId()));
			insuranceItemInListFormBean.setCompanyName(insurance.getInsuranceCompany() != null ? insurance.getInsuranceCompany().getValue() : insurance.getOtherInsuranceCompanyName());
			insuranceItemInListFormBean.setActiveFrom(dateFormat.format(insurance.getActiveFrom()));
			insuranceItemInListFormBean.setActiveTo(dateFormat.format(insurance.getActiveTo()));
			
			insuranceList.add(insuranceItemInListFormBean);
		}
		
		detailFormBean.setVehicle(vehicleFormBean);
		detailFormBean.setOwner(ownerFormBean);
		detailFormBean.setInsurance(new InsuranceFormBean());
		detailFormBean.setInsuranceList(insuranceList);
	}
}
