package org.preznicek.publiclibrary.utils.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Source: <a href="http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression">http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression</a>
 */
public abstract class Validator {

	private Pattern pattern;
	private Matcher matcher;
 
	public Validator() {
		pattern = Pattern.compile(getPattern());
	}
	
	public abstract String getPattern();
 
	/**
	 * Validate hex with regular expression
	 * @param hex hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String hex) {
		matcher = pattern.matcher(hex);
		return matcher.matches();
	}
}
