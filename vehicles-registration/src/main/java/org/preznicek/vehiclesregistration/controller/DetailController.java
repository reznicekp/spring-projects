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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DetailController extends BaseController {

	@Autowired
	private VehicleService vehicleService;
	
	/**
	 * Na zaklade ID v URL z databaze dotahne detail automobilu a tato data zobrazi v needitovatelne podobe.
	 * @param id	ID vozidla.
	 * @return
	 */
	@RequestMapping(value="/detail-car/{id}", method=RequestMethod.GET)
	public ModelAndView showDetailCar(@PathVariable(value="id") Long id) {
		Car car = (Car) vehicleService.getVehicleById(id);
		
		CreateCarFormBean detailFormBean = new CreateCarFormBean();
		setGeneralAttributes(car, detailFormBean);
		
		CarFormBean carFormBean = new CarFormBean();
		carFormBean.setSittingPlacesCount(String.valueOf(car.getSittingPlacesCount()));
		carFormBean.setVolume(String.valueOf(car.getVolume()));
		carFormBean.setFuel(car.getFuel().getValue());
		
		detailFormBean.setCar(carFormBean);
		detailFormBean.getVehicle().setBrand(car.getBrand() != null ? car.getBrand().getValue() : car.getOtherBrandName());
		
		return new ModelAndView("detailCarTile", "detailFormBean", detailFormBean);
	}
	
	/**
	 * Na zaklade ID v URL z databaze dotahne detail motocyklu a tato data zobrazi v needitovatelne podobe.
	 * @param id	ID vozidla.
	 * @return
	 */
	@RequestMapping(value="/detail-motorcycle/{id}", method=RequestMethod.GET)
	public ModelAndView showDetailMotorcycle(@PathVariable(value="id") Long id) {
		Motorcycle motorcycle = (Motorcycle) vehicleService.getVehicleById(id);
		
		CreateMotorcycleFormBean detailFormBean = new CreateMotorcycleFormBean();
		setGeneralAttributes(motorcycle, detailFormBean);
		
		MotorcycleFormBean motorcycleFormBean = new MotorcycleFormBean();
		motorcycleFormBean.setVolume(String.valueOf(motorcycle.getVolume()));
		
		detailFormBean.setMotorcycle(motorcycleFormBean);
		detailFormBean.getVehicle().setBrand(motorcycle.getBrand() != null ? motorcycle.getBrand().getValue() : motorcycle.getOtherBrandName());
		
		return new ModelAndView("detailMotorcycleTile", "detailFormBean", detailFormBean);
	}
	
	/**
	 * Na zaklade ID v URL z databaze dotahne detail nakladniho automobilu a tato data zobrazi v needitovatelne podobe.
	 * @param id	ID vozidla.
	 * @return
	 */
	@RequestMapping(value="/detail-truck/{id}", method=RequestMethod.GET)
	public ModelAndView showDetailTruck(@PathVariable(value="id") Long id) {
		Truck truck = (Truck) vehicleService.getVehicleById(id);
		
		CreateTruckFormBean detailFormBean = new CreateTruckFormBean();
		setGeneralAttributes(truck, detailFormBean);
		
		TruckFormBean truckFormBean = new TruckFormBean();
		truckFormBean.setVolume(String.valueOf(truck.getVolume()));
		truckFormBean.setBodywork(truck.getBodywork().getValue());
		
		detailFormBean.setTruck(truckFormBean);
		detailFormBean.getVehicle().setBrand(truck.getBrand() != null ? truck.getBrand().getValue() : truck.getOtherBrandName());
		
		return new ModelAndView("detailTruckTile", "detailFormBean", detailFormBean);
	}
	
	/**
	 * Na zaklade ID v URL z databaze dotahne detail autobusu a tato data zobrazi v needitovatelne podobe.
	 * @param id	ID vozidla.
	 * @return
	 */
	@RequestMapping(value="/detail-bus/{id}", method=RequestMethod.GET)
	public ModelAndView showDetailBus(@PathVariable(value="id") Long id) {
		Bus bus = (Bus) vehicleService.getVehicleById(id);
		
		CreateBusFormBean detailFormBean = new CreateBusFormBean();
		setGeneralAttributes(bus, detailFormBean);
		
		BusFormBean busFormBean = new BusFormBean();
		busFormBean.setBodywork(bus.getBodywork().getValue());
		busFormBean.setSittingPlacesCount(String.valueOf(bus.getSittingPlacesCount()));
		busFormBean.setVolume(String.valueOf(bus.getVolume()));
		
		detailFormBean.setBus(busFormBean);
		detailFormBean.getVehicle().setBrand(bus.getBrand() != null ? bus.getBrand().getValue() : bus.getOtherBrandName());
		
		return new ModelAndView("detailBusTile", "detailFormBean", detailFormBean);
	}
	
	/**
	 * Na zaklade udaju vozidla vyplni obecne atributy <code>detailFormBean</code> (spolecne pro 
	 * vsechny typy vozidel).
	 * @param vehicle			Vozidlo, jehoz udaje se vyplnuji do <code>detailFormBean</code>.
	 * @param detailFormBean	Needitovatelny formular se spolecnymi udaji pro vsechny typy vozidel.
	 */
	private void setGeneralAttributes(Vehicle vehicle, CreateFormBean detailFormBean) {
		VehicleFormBean vehicleFormBean = new VehicleFormBean();
		vehicleFormBean.setId(String.valueOf(vehicle.getId()));
		vehicleFormBean.setPlateNumber(vehicle.getPlateNumber());
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
