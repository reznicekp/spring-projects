package org.preznicek.vehiclesregistration.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.preznicek.vehiclesregistration.database.domain.vehicle.Vehicle;
import org.preznicek.vehiclesregistration.database.service.VehicleService;
import org.preznicek.vehiclesregistration.model.formbean.SearchFormBean;
import org.preznicek.vehiclesregistration.model.formbean.SearchResultFormBean;
import org.preznicek.vehiclesregistration.model.formbean.validator.SearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController extends BaseController {

	@Autowired
	private VehicleService vehicleService;
	
	@InitBinder(value="searchFormBean")
	public void searchBinder(WebDataBinder binder) {
		binder.setValidator(new SearchValidator());
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ModelAndView showSearch() {
		SearchFormBean searchFormBean = new SearchFormBean();
		return new ModelAndView("searchTile", "searchFormBean", searchFormBean);
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public ModelAndView doSearch(@Valid @ModelAttribute(value="searchFormBean") SearchFormBean searchFormBean, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("searchTile");
		}
		
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		try {
			vehicleList = vehicleService.getVehicleList(searchFormBean);
		} catch (ParseException e) {
			// zde by mely byt datumy validni, takze ParseException by nikdy nemela byt vyhozena
			e.printStackTrace();
		}
		List<SearchResultFormBean> searchResultList = new ArrayList<SearchResultFormBean>();
		
		for (Vehicle vehicle : vehicleList) {
			SearchResultFormBean searchResult = new SearchResultFormBean();
			searchResult.setOrder("1");
			searchResult.setId(String.valueOf(vehicle.getId()));
			searchResult.setVehicleType(vehicle.getVehicleType());
			searchResult.setPlateNumber(vehicle.getPlateNumber());
			searchResult.setBrandAndModel((vehicle.getBrand() != null ? vehicle.getBrand().getValue() : vehicle.getOtherBrandName()) + " " + vehicle.getModel());
			searchResult.setMakingYear(String.valueOf(vehicle.getMakingYear()));
			searchResult.setMotEnd(motDateFormat.format(vehicle.getMotEnd()));
			searchResult.setOwner(vehicle.getOwner().getFirstname() + " " + vehicle.getOwner().getLastname());
			
			searchResultList.add(searchResult);
		}
		
		searchFormBean.setSearchResultFormBeanList(searchResultList);
		
		return new ModelAndView("searchTile");
	}
}
