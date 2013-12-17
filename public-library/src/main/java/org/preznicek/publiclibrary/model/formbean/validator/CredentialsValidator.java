package org.preznicek.publiclibrary.model.formbean.validator;

import org.preznicek.publiclibrary.model.formbean.CredentialsFormBean;

/**
 * Stejny jako <code>RegistrationValidator</code>.
 */
public class CredentialsValidator extends RegistrationValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CredentialsFormBean.class.equals(clazz);
	}
}
