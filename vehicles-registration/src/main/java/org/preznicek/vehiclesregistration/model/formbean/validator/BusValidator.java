package org.preznicek.vehiclesregistration.model.formbean.validator;

import org.apache.commons.validator.routines.IntegerValidator;
import org.preznicek.vehiclesregistration.model.formbean.BusFormBean;
import org.preznicek.vehiclesregistration.model.formbean.CreateBusFormBean;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class BusValidator extends VehicleValidator {

	public boolean supports(Class<?> clazz) {
		return CreateBusFormBean.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		super.validate(target, errors);
		
		CreateBusFormBean createFB = (CreateBusFormBean) target;
		BusFormBean busFB = createFB.getBus();
		
		if (StringUtils.isEmpty(busFB.getSittingPlacesCount())) {
			errors.rejectValue("bus.sittingPlacesCount", "error.required.sittingPlacesCount");
		} else if (!IntegerValidator.getInstance().isValid(busFB.getSittingPlacesCount())) {
			errors.rejectValue("bus.sittingPlacesCount", "error.format.sittingPlacesCount");
		}
		
		if (StringUtils.isEmpty(busFB.getVolume())) {
			errors.rejectValue("bus.volume", "error.required.volume");
		} else if (!IntegerValidator.getInstance().isValid(busFB.getVolume())) {
			errors.rejectValue("bus.volume", "error.format.volume");
		}
		
		if (StringUtils.isEmpty(busFB.getBodywork())) {
			errors.rejectValue("bus.bodywork", "error.required.bodywork");
		}
	}
}
