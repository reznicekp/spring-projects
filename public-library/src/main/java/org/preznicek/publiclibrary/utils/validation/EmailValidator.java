package org.preznicek.publiclibrary.utils.validation;

public class EmailValidator extends Validator {

	@Override
	public String getPattern() {
		return "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	}
}
