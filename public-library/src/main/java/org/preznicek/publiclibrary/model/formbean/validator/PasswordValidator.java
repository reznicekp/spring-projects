package org.preznicek.publiclibrary.model.formbean.validator;

import org.preznicek.publiclibrary.model.formbean.PasswordFormBean;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PasswordValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return PasswordFormBean.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		PasswordFormBean passwordFB = (PasswordFormBean) target;
		
		if (StringUtils.isEmpty(passwordFB.getOldPassword())) {
			errors.rejectValue("oldPassword", "error.required.oldPassword");
		}
		
		if (StringUtils.isEmpty(passwordFB.getNewPassword())) {
			errors.rejectValue("newPassword", "error.required.newPassword");
		}
		
		if (StringUtils.isEmpty(passwordFB.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "error.required.confirmPassword");
		}
		
		if (!passwordFB.getNewPassword().equals(passwordFB.getConfirmPassword())) {
			errors.reject("error.invalid.newConfirmPasswordNotSame");
		}
	}
}
