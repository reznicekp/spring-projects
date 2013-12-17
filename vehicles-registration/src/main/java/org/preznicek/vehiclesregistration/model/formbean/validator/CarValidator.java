package org.preznicek.vehiclesregistration.model.formbean.validator;

import org.apache.commons.validator.routines.IntegerValidator;
import org.preznicek.vehiclesregistration.model.formbean.CarFormBean;
import org.preznicek.vehiclesregistration.model.formbean.CreateCarFormBean;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class CarValidator extends VehicleValidator {

	public boolean supports(Class<?> clazz) {
		return CreateCarFormBean.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		super.validate(target, errors);
		
		CreateCarFormBean createFB = (CreateCarFormBean) target;
		CarFormBean carFB = createFB.getCar();
		
		if (StringUtils.isEmpty(carFB.getSittingPlacesCount())) {
			errors.rejectValue("car.sittingPlacesCount", "error.required.sittingPlacesCount");
		} else if (!IntegerValidator.getInstance().isValid(carFB.getSittingPlacesCount())) {
			errors.rejectValue("car.sittingPlacesCount", "error.format.sittingPlacesCount");
		}
		
		if (StringUtils.isEmpty(carFB.getVolume())) {
			errors.rejectValue("car.volume", "error.required.volume");
		} else if (!IntegerValidator.getInstance().isValid(carFB.getVolume())) {
			errors.rejectValue("car.volume", "error.format.volume");
		}
		
		if (StringUtils.isEmpty(carFB.getFuel())) {
			errors.rejectValue("car.fuel", "error.required.fuel");
		}
	}
}
