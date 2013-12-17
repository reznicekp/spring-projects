package org.preznicek.vehiclesregistration.model.formbean.validator;

import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.preznicek.vehiclesregistration.model.formbean.SearchFormBean;
import org.preznicek.vehiclesregistration.utils.Constants;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SearchValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return SearchFormBean.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		SearchFormBean searchFB = (SearchFormBean) target;
		
		if (!StringUtils.isEmpty(searchFB.getSearchVehicleFormBean().getPlateNumber()) && searchFB.getSearchVehicleFormBean().getPlateNumber().length() > 9) {
			errors.rejectValue("searchVehicleFormBean.plateNumber", "error.length.plateNumber");
		}
		
		if (!StringUtils.isEmpty(searchFB.getSearchVehicleFormBean().getMakingYearFrom()) && !IntegerValidator.getInstance().isValid(searchFB.getSearchVehicleFormBean().getMakingYearFrom())) {
			errors.rejectValue("searchVehicleFormBean.makingYearFrom", "error.format.makingYear");
		}
		
		if (!StringUtils.isEmpty(searchFB.getSearchVehicleFormBean().getMakingYearTo()) && !IntegerValidator.getInstance().isValid(searchFB.getSearchVehicleFormBean().getMakingYearTo())) {
			errors.rejectValue("searchVehicleFormBean.makingYearTo", "error.format.makingYear");
		}
		
		if (!StringUtils.isEmpty(searchFB.getSearchVehicleFormBean().getMotEndFrom()) && !DateValidator.getInstance().isValid(searchFB.getSearchVehicleFormBean().getMotEndFrom(), Constants.MOT_DATE_FORMAT)) {
			errors.rejectValue("searchVehicleFormBean.motEndFrom", "error.format.motEnd");
		}
		
		if (!StringUtils.isEmpty(searchFB.getSearchVehicleFormBean().getMotEndTo()) && !DateValidator.getInstance().isValid(searchFB.getSearchVehicleFormBean().getMotEndTo(), Constants.MOT_DATE_FORMAT)) {
			errors.rejectValue("searchVehicleFormBean.motEndTo", "error.format.motEnd");
		}
		
		if (!StringUtils.isEmpty(searchFB.getSearchOwnerFormBean().getFirstname()) && searchFB.getSearchOwnerFormBean().getFirstname().length() > 50) {
			errors.rejectValue("searchOwnerFormBean.firstname", "error.length.firstname");
		}
		
		if (!StringUtils.isEmpty(searchFB.getSearchOwnerFormBean().getLastname()) && searchFB.getSearchOwnerFormBean().getLastname().length() > 50) {
			errors.rejectValue("searchOwnerFormBean.lastname", "error.length.lastname");
		}
		
		if (!StringUtils.isEmpty(searchFB.getSearchOwnerFormBean().getBirthCertificateNumber()) && searchFB.getSearchOwnerFormBean().getBirthCertificateNumber().length() > 11) {
			errors.rejectValue("searchOwnerFormBean.birthCertificateNumber", "error.length.birthCertificateNumber");
		}
	}
}
