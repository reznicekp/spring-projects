package org.preznicek.vehiclesregistration.model.formbean.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.validator.routines.DateValidator;
import org.preznicek.vehiclesregistration.model.formbean.CreateFormBean;
import org.preznicek.vehiclesregistration.model.formbean.InsuranceFormBean;
import org.preznicek.vehiclesregistration.utils.Constants;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class InsuranceValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		return CreateFormBean.class.equals(clazz.getSuperclass());
	}

	public void validate(Object target, Errors errors) {
		CreateFormBean createFB = (CreateFormBean) target;
		InsuranceFormBean insuranceFB = createFB.getInsurance();
		
		if (StringUtils.isEmpty(createFB.getVehicle().getId())) {
			errors.rejectValue("vehicle.plateNumber", "error.noSuchVehicle");
		}
		
		if (!StringUtils.isEmpty(insuranceFB.getCompanyCode()) && !StringUtils.isEmpty(insuranceFB.getOtherCompanyName())) {
			errors.rejectValue("insurance.companyCode", "error.notStrict.company");
			errors.rejectValue("insurance.otherCompanyName", "empty");
		} else if (StringUtils.isEmpty(insuranceFB.getCompanyCode()) && StringUtils.isEmpty(insuranceFB.getOtherCompanyName())) {
			errors.rejectValue("insurance.companyCode", "error.required.company");
			errors.rejectValue("insurance.otherCompanyName", "empty");
		} else if (!StringUtils.isEmpty(insuranceFB.getOtherCompanyName()) && insuranceFB.getOtherCompanyName().length() > 50) {
			errors.rejectValue("insurance.otherCompanyName", "error.length.company");
		}
		
		boolean activeFrom = true;
		if (StringUtils.isEmpty(insuranceFB.getActiveFrom())) {
			errors.rejectValue("insurance.activeFrom", "error.required.activeFrom");
			activeFrom = false;
		} else if (!DateValidator.getInstance().isValid(insuranceFB.getActiveFrom(), Constants.DATE_FORMAT)) {
			errors.rejectValue("insurance.activeFrom", "error.format.activeFrom");
			activeFrom = false;
		}
		
		boolean activeTo = true;
		if (StringUtils.isEmpty(insuranceFB.getActiveTo())) {
			errors.rejectValue("insurance.activeTo", "error.required.activeTo");
			activeTo = false;
		} else if (!DateValidator.getInstance().isValid(insuranceFB.getActiveTo(), Constants.DATE_FORMAT)) {
			errors.rejectValue("insurance.activeTo", "error.format.activeTo");
			activeTo = false;
		}
		
		if (activeFrom && activeTo) {
			try {
				Date dateActiveFrom = new SimpleDateFormat(Constants.DATE_FORMAT).parse(insuranceFB.getActiveFrom());
				Date dateActiveTo = new SimpleDateFormat(Constants.DATE_FORMAT).parse(insuranceFB.getActiveTo());
				if (dateActiveTo.getTime() - dateActiveFrom.getTime() < 0) {
					errors.rejectValue("insurance.activeFrom", "error.comparison.activeFromActiveTo");
					errors.rejectValue("insurance.activeTo", "empty");
				}
			} catch (ParseException e) {
				// zde by mely byt datumy vyplnene a validni, takze ParseException by nikdy nemela byt vyhozena
				e.printStackTrace();
			}
		}
	}
}
