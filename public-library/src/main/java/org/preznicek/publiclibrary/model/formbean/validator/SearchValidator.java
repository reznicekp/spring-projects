package org.preznicek.publiclibrary.model.formbean.validator;

import org.preznicek.publiclibrary.model.formbean.SearchFormBean;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SearchValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return SearchFormBean.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		SearchFormBean searchFB = (SearchFormBean) target;
		
		if (StringUtils.isEmpty(searchFB.getSearchedSubject())) {
			errors.rejectValue("searchedSubject", "error.required.searchedSubject");
		}
	}
}
