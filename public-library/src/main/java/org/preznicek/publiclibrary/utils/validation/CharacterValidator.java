package org.preznicek.publiclibrary.utils.validation;

public class CharacterValidator extends Validator {

	@Override
	public String getPattern() {
		return "^[_A-Za-z0-9- ]+$";
	}
}
