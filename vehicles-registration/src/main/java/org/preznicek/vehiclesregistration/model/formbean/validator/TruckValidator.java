package org.preznicek.vehiclesregistration.model.formbean.validator;

import org.apache.commons.validator.routines.IntegerValidator;
import org.preznicek.vehiclesregistration.model.formbean.CreateTruckFormBean;
import org.preznicek.vehiclesregistration.model.formbean.TruckFormBean;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class TruckValidator extends VehicleValidator {

	public boolean supports(Class<?> clazz) {
		return CreateTruckFormBean.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		super.validate(target, errors);
		
		CreateTruckFormBean createFB = (CreateTruckFormBean) target;
		TruckFormBean truckFB = createFB.getTruck();
		
		if (StringUtils.isEmpty(truckFB.getVolume())) {
			errors.rejectValue("truck.volume", "error.required.volume");
		} else if (!IntegerValidator.getInstance().isValid(truckFB.getVolume())) {
			errors.rejectValue("truck.volume", "error.format.volume");
		}
		
		if (StringUtils.isEmpty(truckFB.getBodywork())) {
			errors.rejectValue("truck.bodywork", "error.required.bodywork");
		}
	}
}
