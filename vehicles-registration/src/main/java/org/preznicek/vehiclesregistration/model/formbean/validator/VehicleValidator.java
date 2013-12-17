package org.preznicek.vehiclesregistration.model.formbean.validator;

import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.preznicek.vehiclesregistration.model.formbean.CreateFormBean;
import org.preznicek.vehiclesregistration.utils.Constants;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class VehicleValidator implements Validator {

	public void validate(Object target, Errors errors) {
		CreateFormBean createFB = (CreateFormBean) target;
		
		if (StringUtils.isEmpty(createFB.getVehicle().getPlateNumber())) {
			errors.rejectValue("vehicle.plateNumber", "error.required.plateNumber");
		} else if (createFB.getVehicle().getPlateNumber().length() > 9) {
			errors.rejectValue("vehicle.plateNumber", "error.length.plateNumber");
		}
		
		if (!StringUtils.isEmpty(createFB.getVehicle().getBrand()) && !StringUtils.isEmpty(createFB.getVehicle().getOtherBrandName())) {
			errors.rejectValue("vehicle.brand", "error.notStrict.brand");
			errors.rejectValue("vehicle.otherBrandName", "empty");
		} else if (StringUtils.isEmpty(createFB.getVehicle().getBrand()) && StringUtils.isEmpty(createFB.getVehicle().getOtherBrandName())) {
			errors.rejectValue("vehicle.brand", "error.required.brand");
			errors.rejectValue("vehicle.otherBrandName", "empty");
		} else if (!StringUtils.isEmpty(createFB.getVehicle().getOtherBrandName()) && createFB.getVehicle().getOtherBrandName().length() > 50) {
			errors.rejectValue("vehicle.otherBrandName", "error.length.brand");
		}
		
		if (StringUtils.isEmpty(createFB.getVehicle().getModel())) {
			errors.rejectValue("vehicle.model", "error.required.model");
		} else if (createFB.getVehicle().getModel().length() > 50) {
			errors.rejectValue("vehicle.model", "error.length.model");
		}
		
		if (StringUtils.isEmpty(createFB.getVehicle().getMakingYear())) {
			errors.rejectValue("vehicle.makingYear", "error.required.makingYear");
		} else if (!IntegerValidator.getInstance().isValid(createFB.getVehicle().getMakingYear())) {
			errors.rejectValue("vehicle.makingYear", "error.format.makingYear");
		}
		
		if (!StringUtils.isEmpty(createFB.getVehicle().getMotEnd()) && !DateValidator.getInstance().isValid(createFB.getVehicle().getMotEnd(), Constants.MOT_DATE_FORMAT)) {
			errors.rejectValue("vehicle.motEnd", "error.format.motEnd");
		}
		
		if (!StringUtils.isEmpty(createFB.getVehicle().getWeight()) && !IntegerValidator.getInstance().isValid(createFB.getVehicle().getWeight())) {
			errors.rejectValue("vehicle.weight", "error.format.weight");
		}
		
		if (StringUtils.isEmpty(createFB.getOwner().getFirstname())) {
			errors.rejectValue("owner.firstname", "error.required.firstname");
		} else if (createFB.getOwner().getFirstname().length() > 50) {
			errors.rejectValue("owner.firstname", "error.length.firstname");
		}
		
		if (StringUtils.isEmpty(createFB.getOwner().getLastname())) {
			errors.rejectValue("owner.lastname", "error.required.lastname");
		} else if (createFB.getOwner().getLastname().length() > 50) {
			errors.rejectValue("owner.lastname", "error.length.lastname");
		}
		
		if (StringUtils.isEmpty(createFB.getOwner().getBirthCertificateNumber())) {
			errors.rejectValue("owner.birthCertificateNumber", "error.required.birthCertificateNumber");
		} else if (createFB.getOwner().getBirthCertificateNumber().length() > 11) {
			errors.rejectValue("owner.birthCertificateNumber", "error.length.birthCertificateNumber");
		}
		
		if (StringUtils.isEmpty(createFB.getOwner().getAddress())) {
			errors.rejectValue("owner.address", "error.required.address");
		}
		
		if (StringUtils.isEmpty(createFB.getOwner().getPhone1())) {
			errors.rejectValue("owner.phone1", "error.required.phone1");
		} else if (createFB.getOwner().getPhone1().length() > 20) {
			errors.rejectValue("owner.phone1", "error.length.phone1");
		}
		
		if (!StringUtils.isEmpty(createFB.getOwner().getPhone2()) && createFB.getOwner().getPhone2().length() > 20) {
			errors.rejectValue("owner.phone2", "error.length.phone2");
		}
	}
}
