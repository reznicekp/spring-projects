package org.preznicek.publiclibrary.model.formbean.validator;

import org.preznicek.publiclibrary.model.formbean.LoginFormBean;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return LoginFormBean.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		LoginFormBean loginFB = (LoginFormBean) target;
		
		if (StringUtils.isEmpty(loginFB.getEmail())) {
			errors.rejectValue("email", "error.required.email");
		}
		
		if (StringUtils.isEmpty(loginFB.getPassword())) {
			errors.rejectValue("password", "error.required.password");
		}
	}
}
