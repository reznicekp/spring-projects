package org.preznicek.publiclibrary.model.formbean.validator;

import org.preznicek.publiclibrary.model.formbean.FeedbackFormBean;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FeedbackValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return FeedbackFormBean.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		FeedbackFormBean feedbackFB = (FeedbackFormBean) target;
		
		if (feedbackFB.getRentTiming() == null) {
			errors.rejectValue("rentTiming", "error.required.rentTiming");
		}
		
		if (feedbackFB.getBookDamage() == null) {
			errors.rejectValue("bookDamage", "error.required.bookDamage");
		}
		
		if (feedbackFB.getBookLoss() == null) {
			errors.rejectValue("bookLoss", "error.required.bookLoss");
		}
	}
}
