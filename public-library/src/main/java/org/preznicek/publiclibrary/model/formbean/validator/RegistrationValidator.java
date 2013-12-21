package org.preznicek.publiclibrary.model.formbean.validator;

import java.util.Calendar;

import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.preznicek.publiclibrary.model.formbean.RegistrationFormBean;
import org.preznicek.publiclibrary.utils.validation.CharacterValidator;
import org.preznicek.publiclibrary.utils.validation.EmailValidator;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RegistrationValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return RegistrationFormBean.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		RegistrationFormBean registrationFB = (RegistrationFormBean) target;
		
		if (StringUtils.isEmpty(registrationFB.getLastname())) {
			errors.rejectValue("lastname", "error.required.lastname");
		} else {
			CharacterValidator characterValidator = new CharacterValidator();
			if (!characterValidator.validate(registrationFB.getLastname())) {
				errors.rejectValue("lastname", "error.invalid.lastname");
			}
		}
		
		if (StringUtils.isEmpty(registrationFB.getEmail())) {
			errors.rejectValue("email", "error.required.email");
		} else {
			EmailValidator emailValidator = new EmailValidator();
			if (!emailValidator.validate(registrationFB.getEmail())) {
				errors.rejectValue("email", "error.invalid.email");
			}
		}
		
		if (StringUtils.isEmpty(registrationFB.getBirthYear())) {
			errors.rejectValue("birthYear", "error.required.birthYear");
		} else {
			if (!IntegerValidator.getInstance().isValid(registrationFB.getBirthYear())) {
				errors.rejectValue("birthYear", "error.invalid.birthYear");
			} else {
				Calendar calendar = Calendar.getInstance();
				int yearToday = calendar.get(Calendar.YEAR);
				if (!IntegerValidator.getInstance().isInRange(Integer.parseInt(registrationFB.getBirthYear()), 1900, yearToday)) {
					errors.rejectValue("birthYear", "error.invalid.birthYearRange", new String[] {String.valueOf(yearToday)}, "");
				}
			}
		}
		
		if (StringUtils.isEmpty(registrationFB.getGpsLatitude())) {
			errors.rejectValue("gpsLatitude", "error.required.gpsLatitude");
		} else {
			BigDecimalValidator bigDecimalValidator = new BigDecimalValidator();
			if (!bigDecimalValidator.isValid(registrationFB.getGpsLatitude())) {//, LocaleContextHolder.getLocale())) {
				errors.rejectValue("gpsLatitude", "error.invalid.gpsLatitude");
			}
		}
		
		if (StringUtils.isEmpty(registrationFB.getGpsLongitude())) {
			errors.rejectValue("gpsLongitude", "error.required.gpsLongitude");
		} else {
			BigDecimalValidator bigDecimalValidator = new BigDecimalValidator();
			if (!bigDecimalValidator.isValid(registrationFB.getGpsLatitude())) {
				errors.rejectValue("gpsLongitude", "error.invalid.gpsLongitude");
			}
		}
	}
}
