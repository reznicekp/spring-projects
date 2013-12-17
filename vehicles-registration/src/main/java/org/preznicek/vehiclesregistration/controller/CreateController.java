package org.preznicek.vehiclesregistration.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.text.WordUtils;
import org.preznicek.vehiclesregistration.database.domain.Owner;
import org.preznicek.vehiclesregistration.database.domain.codetable.BodyworkBusCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BodyworkTruckCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.BrandCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.FuelCT;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Bus;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Car;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Motorcycle;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Truck;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Vehicle;
import org.preznicek.vehiclesregistration.database.service.OwnerService;
import org.preznicek.vehiclesregistration.database.service.VehicleService;
import org.preznicek.vehiclesregistration.model.formbean.CreateBusFormBean;
import org.preznicek.vehiclesregistration.model.formbean.CreateCarFormBean;
import org.preznicek.vehiclesregistration.model.formbean.CreateFormBean;
import org.preznicek.vehiclesregistration.model.formbean.CreateMotorcycleFormBean;
import org.preznicek.vehiclesregistration.model.formbean.CreateTruckFormBean;
import org.preznicek.vehiclesregistration.model.formbean.SearchResultFormBean;
import org.preznicek.vehiclesregistration.model.formbean.validator.BusValidator;
import org.preznicek.vehiclesregistration.model.formbean.validator.CarValidator;
import org.preznicek.vehiclesregistration.model.formbean.validator.InsuranceValidator;
import org.preznicek.vehiclesregistration.model.formbean.validator.MotorcycleValidator;
import org.preznicek.vehiclesregistration.model.formbean.validator.TruckValidator;
import org.preznicek.vehiclesregistration.utils.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CreateController extends BaseController {

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private OwnerService ownerService;
	
	@InitBinder(value="createFormBean")
	public void createBinder(WebDataBinder binder, @RequestParam(value="saveVehicle", required=false) String saveVehicle, @RequestParam(value="saveInsurance", required=false) String saveInsurance) {
		if (saveVehicle != null) {
			if (binder.getTarget() instanceof CreateCarFormBean) {
				binder.setValidator(new CarValidator());
			} else if (binder.getTarget() instanceof CreateMotorcycleFormBean) {
				binder.setValidator(new MotorcycleValidator());
			} else if (binder.getTarget() instanceof CreateTruckFormBean) {
				binder.setValidator(new TruckValidator());
			} else if (binder.getTarget() instanceof CreateBusFormBean) {
				binder.setValidator(new BusValidator());
			}
		} else if (saveInsurance != null) {
			binder.setValidator(new InsuranceValidator());
		}
	}
	
	@RequestMapping(value="/create-car", method=RequestMethod.GET)
	public ModelAndView showCreateCar() {
		return new ModelAndView("createCarTile", "createFormBean", new CreateCarFormBean());
	}
	
	@RequestMapping(value="/create-motorcycle", method=RequestMethod.GET)
	public ModelAndView showCreateMotorcycle() {
		return new ModelAndView("createMotorcycleTile", "createFormBean", new CreateMotorcycleFormBean());
	}
	
	@RequestMapping(value="/create-truck", method=RequestMethod.GET)
	public ModelAndView showCreateTruck() {
		return new ModelAndView("createTruckTile", "createFormBean", new CreateTruckFormBean());
	}
	
	@RequestMapping(value="/create-bus", method=RequestMethod.GET)
	public ModelAndView showCreateBus() {
		return new ModelAndView("createBusTile", "createFormBean", new CreateBusFormBean());
	}
	
	@RequestMapping(value="/upsert-car", params="saveVehicle", method=RequestMethod.POST)
	public ModelAndView upsertCar(@Valid @ModelAttribute(value="createFormBean") CreateCarFormBean createFormBean, BindingResult result) throws ParseException {
		if (result.hasErrors()) {
			return new ModelAndView("createCarTile");
		}
		
		Car car = new Car();
		setGeneralAttributes(car, createFormBean);
		car.setSittingPlacesCount(Integer.valueOf(createFormBean.getCar().getSittingPlacesCount()));
		car.setVolume(Integer.valueOf(createFormBean.getCar().getVolume()));
		car.setFuel((FuelCT) codeTableService.getCodeTableRow(FuelCT.class, Integer.valueOf(createFormBean.getCar().getFuel())));
		
		vehicleService.upsert(car);
		
		return new ModelAndView(new RedirectView("/detail-car/" + car.getId(), true));
	}
	
	@RequestMapping(value="/upsert-motorcycle", params="saveVehicle", method=RequestMethod.POST)
	public ModelAndView upsertMotorcycle(@Valid @ModelAttribute(value="createFormBean") CreateMotorcycleFormBean createFormBean, BindingResult result) throws ParseException {
		if (result.hasErrors()) {
			return new ModelAndView("createMotorcycleTile");
		}
		
		Motorcycle motorcycle = new Motorcycle();
		setGeneralAttributes(motorcycle, createFormBean);
		motorcycle.setVolume(Integer.valueOf(createFormBean.getMotorcycle().getVolume()));
		
		vehicleService.upsert(motorcycle);
		
		return new ModelAndView(new RedirectView("/detail-motorcycle/" + motorcycle.getId(), true));
	}
	
	@RequestMapping(value="/upsert-truck", params="saveVehicle", method=RequestMethod.POST)
	public ModelAndView upsertTruck(@Valid @ModelAttribute(value="createFormBean") CreateTruckFormBean createFormBean, BindingResult result) throws ParseException {
		if (result.hasErrors()) {
			return new ModelAndView("createTruckTile");
		}
		
		Truck truck = new Truck();
		setGeneralAttributes(truck, createFormBean);
		truck.setVolume(Integer.valueOf(createFormBean.getTruck().getVolume()));
		truck.setBodywork((BodyworkTruckCT) codeTableService.getCodeTableRow(BodyworkTruckCT.class, Integer.valueOf(createFormBean.getTruck().getBodywork())));
		
		vehicleService.upsert(truck);
		
		return new ModelAndView(new RedirectView("/detail-truck/" + truck.getId(), true));
	}
	
	@RequestMapping(value="/upsert-bus", params="saveVehicle", method=RequestMethod.POST)
	public ModelAndView upsertBus(@Valid @ModelAttribute(value="createFormBean") CreateBusFormBean createFormBean, BindingResult result) throws ParseException {
		if (result.hasErrors()) {
			return new ModelAndView("createBusTile");
		}
		
		Bus bus = new Bus();
		setGeneralAttributes(bus, createFormBean);
		bus.setBodywork((BodyworkBusCT) codeTableService.getCodeTableRow(BodyworkBusCT.class, Integer.valueOf(createFormBean.getBus().getBodywork())));
		bus.setSittingPlacesCount(Integer.valueOf(createFormBean.getBus().getSittingPlacesCount()));
		bus.setVolume(Integer.valueOf(createFormBean.getBus().getVolume()));
		
		vehicleService.upsert(bus);
		
		return new ModelAndView(new RedirectView("/detail-bus/" + bus.getId(), true));
	}
	
	private void setGeneralAttributes(Vehicle vehicle, CreateFormBean createFormBean) throws ParseException {
		if (!StringUtils.isEmpty(createFormBean.getVehicle().getId())) {
			vehicle.setId(Long.valueOf(createFormBean.getVehicle().getId()));
		}
		if (vehicle instanceof Car) {
			vehicle.setVehicleType(VehicleType.CAR.toString());
		} else if (vehicle instanceof Motorcycle) {
			vehicle.setVehicleType(VehicleType.MOTORCYCLE.toString());
		} else if (vehicle instanceof Truck) {
			vehicle.setVehicleType(VehicleType.TRUCK.toString());
		} else if (vehicle instanceof Bus) {
			vehicle.setVehicleType(VehicleType.BUS.toString());
		}
		vehicle.setPlateNumber(createFormBean.getVehicle().getPlateNumber());
		vehicle.setBrand((BrandCT) codeTableService.getCodeTableRow(BrandCT.class, Integer.valueOf(createFormBean.getVehicle().getBrand())));
		vehicle.setOtherBrandName(createFormBean.getVehicle().getOtherBrandName());
		vehicle.setModel(createFormBean.getVehicle().getModel());
		vehicle.setMakingYear(Integer.valueOf(createFormBean.getVehicle().getMakingYear()));
		vehicle.setMotEnd(motDateFormat.parse(createFormBean.getVehicle().getMotEnd()));
		vehicle.setWeight(Integer.valueOf(createFormBean.getVehicle().getWeight()));
		
		Owner owner = ownerService.getOwnerByBCN(createFormBean.getOwner().getBirthCertificateNumber());
		if (owner == null) {
			owner = new Owner();
		}
		owner.setFirstname(createFormBean.getOwner().getFirstname());
		owner.setLastname(createFormBean.getOwner().getLastname());
		owner.setBirthCertificateNumber(createFormBean.getOwner().getBirthCertificateNumber());
		owner.setAddress(createFormBean.getOwner().getAddress());
		owner.setPhone1(createFormBean.getOwner().getPhone1());
		owner.setPhone2(createFormBean.getOwner().getPhone2());
		vehicle.setOwner(owner);
	}
	
	@RequestMapping(value="/upsert-car", params="getOwner", method=RequestMethod.POST)
	public ModelAndView getOwnerCar(@ModelAttribute(value="createFormBean") CreateCarFormBean createFormBean, BindingResult result) {
		return getOwner("car", createFormBean, result);
	}
	
	@RequestMapping(value="/upsert-motorcycle", params="getOwner", method=RequestMethod.POST)
	public ModelAndView getOwnerMotorcycle(@ModelAttribute(value="createFormBean") CreateMotorcycleFormBean createFormBean, BindingResult result) {
		return getOwner("motorcycle", createFormBean, result);
	}
	
	@RequestMapping(value="/upsert-truck", params="getOwner", method=RequestMethod.POST)
	public ModelAndView getOwnerTruck(@ModelAttribute(value="createFormBean") CreateTruckFormBean createFormBean, BindingResult result) {
		return getOwner("truck", createFormBean, result);
	}
	
	@RequestMapping(value="/upsert-bus", params="getOwner", method=RequestMethod.POST)
	public ModelAndView getOwnerBus(@ModelAttribute(value="createFormBean") CreateBusFormBean createFormBean, BindingResult result) {
		return getOwner("bus", createFormBean, result);
	}
	
	private ModelAndView getOwner(String vehicleType, CreateFormBean createFormBean, BindingResult result) {
		Owner owner = ownerService.getOwnerByBCN(createFormBean.getOwner().getBirthCertificateNumber());
		if (owner == null) {
			result.rejectValue("owner.birthCertificateNumber", "error.noSuchOwner");
		} else {
			createFormBean.getOwner().setId(String.valueOf(owner.getId()));
			createFormBean.getOwner().setFirstname(owner.getFirstname());
			createFormBean.getOwner().setLastname(owner.getLastname());
			createFormBean.getOwner().setBirthCertificateNumber(owner.getBirthCertificateNumber());
			createFormBean.getOwner().setAddress(owner.getAddress());
			createFormBean.getOwner().setPhone1(owner.getPhone1());
			createFormBean.getOwner().setPhone2(owner.getPhone2());
			
			List<Vehicle> vehiclesOfOwner = vehicleService.getVehicleListByOwnerId(Long.valueOf(createFormBean.getOwner().getId()));
			List<SearchResultFormBean> searchResultList = new ArrayList<SearchResultFormBean>();
			for (Vehicle vehicleOfOwner : vehiclesOfOwner) {
				if (vehicleOfOwner.getId().longValue() == Long.valueOf(createFormBean.getOwner().getId()).longValue()) {	// aktualne zobrazovane vozidlo se v seznamu nezobrazuje
					continue;
				}
				
				SearchResultFormBean searchResult = new SearchResultFormBean();
				searchResult.setId(String.valueOf(vehicleOfOwner.getId()));
				searchResult.setOrder("1");
				searchResult.setVehicleType(vehicleOfOwner.getVehicleType());
				searchResult.setPlateNumber(vehicleOfOwner.getPlateNumber());
				searchResult.setBrandAndModel((vehicleOfOwner.getBrand() != null ? vehicleOfOwner.getBrand().getValue() : vehicleOfOwner.getOtherBrandName()) + " " + vehicleOfOwner.getModel());
				searchResult.setMakingYear(String.valueOf(vehicleOfOwner.getMakingYear()));
				searchResult.setMotEnd(vehicleOfOwner.getMotEnd() != null ? motDateFormat.format(vehicleOfOwner.getMotEnd()) : "");
				
				searchResultList.add(searchResult);
			}
			createFormBean.getOwner().setVehiclesOfOwner(searchResultList);
		}
		
		return new ModelAndView("create" + WordUtils.capitalize(vehicleType) + "Tile");
	}
}
