package org.preznicek.publiclibrary.model.formbean.validator;

import org.apache.commons.validator.routines.IntegerValidator;
import org.preznicek.publiclibrary.model.formbean.BookFormBean;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BookValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return BookFormBean.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		BookFormBean bookFB = (BookFormBean) target;
		
		if (StringUtils.isEmpty(bookFB.getName())) {
			errors.rejectValue("name", "error.required.name");
		}
		if (StringUtils.isEmpty(bookFB.getAuthor())) {
			errors.rejectValue("author", "error.required.author");
		}
		if (!StringUtils.isEmpty(bookFB.getYear())) {
			if (!IntegerValidator.getInstance().isValid(bookFB.getYear())) {
				errors.rejectValue("year", "error.invalid.year");
			}
		}
	}
}
