package org.preznicek.vehiclesregistration.model.formbean.validator;

import org.apache.commons.validator.routines.IntegerValidator;
import org.preznicek.vehiclesregistration.model.formbean.CreateMotorcycleFormBean;
import org.preznicek.vehiclesregistration.model.formbean.MotorcycleFormBean;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class MotorcycleValidator extends VehicleValidator {

	public boolean supports(Class<?> clazz) {
		return CreateMotorcycleFormBean.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		super.validate(target, errors);
		
		CreateMotorcycleFormBean createFB = (CreateMotorcycleFormBean) target;
		MotorcycleFormBean motorcycleFB = createFB.getMotorcycle();
		
		if (StringUtils.isEmpty(motorcycleFB.getVolume())) {
			errors.rejectValue("motorcycle.volume", "error.required.volume");
		} else if (!IntegerValidator.getInstance().isValid(motorcycleFB.getVolume())) {
			errors.rejectValue("motorcycle.volume", "error.format.volume");
		}
	}
}
