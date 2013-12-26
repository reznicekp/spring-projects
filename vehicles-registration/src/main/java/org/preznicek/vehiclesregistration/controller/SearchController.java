package org.preznicek.vehiclesregistration.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.preznicek.vehiclesregistration.database.PageData;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Vehicle;
import org.preznicek.vehiclesregistration.database.service.VehicleService;
import org.preznicek.vehiclesregistration.model.formbean.SearchFormBean;
import org.preznicek.vehiclesregistration.model.formbean.SearchResultFormBean;
import org.preznicek.vehiclesregistration.model.formbean.validator.SearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController extends PageableController {

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
	public ModelAndView doSearch(@Valid @ModelAttribute(value="searchFormBean") SearchFormBean searchFormBean, BindingResult result, HttpServletRequest request, @Qualifier(value="searchResultFormBeanList") Pageable pageable) {
		if (result.hasErrors()) {
			return new ModelAndView("searchTile");
		}
		
		int currentPageNumber = getCurrentPageNumber(request, pageable);
		
		PageData vehiclePD = null;
		try {
			vehiclePD = vehicleService.getVehicleList(searchFormBean, currentPageNumber);
		} catch (ParseException e) {
			// zde by mely byt datumy validni, takze ParseException by nikdy nemela byt vyhozena
			e.printStackTrace();
		}
		List<Vehicle> vehicleList = (List<Vehicle>) vehiclePD.getItems();
		HashMap<String, Boolean> pagingButtons = managePagingButtons("searchResultFormBeanList", currentPageNumber, vehiclePD.getTotalCount());
		
		List<SearchResultFormBean> searchResultList = new ArrayList<SearchResultFormBean>();
		
		for (Vehicle vehicle : vehicleList) {
			SearchResultFormBean searchResult = new SearchResultFormBean();
			searchResult.setId(String.valueOf(vehicle.getId()));
			searchResult.setVehicleType(vehicle.getVehicleType());
			searchResult.setPlateNumber(vehicle.getPlateNumber());
			searchResult.setBrandAndModel(vehicle.getOtherBrandName() + " " + vehicle.getModel());
			searchResult.setMakingYear(String.valueOf(vehicle.getMakingYear()));
			searchResult.setMotEnd(vehicle.getMotEnd() != null ? motDateFormat.format(vehicle.getMotEnd()) : "");
			searchResult.setOwner(vehicle.getOwner().getFirstname() + " " + vehicle.getOwner().getLastname());
			
			searchResultList.add(searchResult);
		}
		
		searchFormBean.setSearchResultFormBeanList(searchResultList);
		searchFormBean.setSearchResultFormBeanList_page(Integer.valueOf(currentPageNumber));
		
		ModelAndView mav = new ModelAndView("searchTile");
		mav.addAllObjects(pagingButtons);
		return mav;
	}
}
