package org.preznicek.publiclibrary.controller.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

public class NullEditor extends PropertyEditorSupport {

	/**
	 * Pokud se z databaze vrati hodnota <code>null</code>, do formulare se zapise prazdny retezec.
	 */
	@Override
	public String getAsText() {
		String text = super.getAsText();
		if ("null".equals(text)) {
			text = "";
		}
		return text;
	}

	/**
	 * Pokud se z formulare odesle prazdny retezec, do databaze se zapise hodnota <code>null</code>.
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isEmpty(text)) {
			setValue(null);
		} else {
			setValue(text);
		}
	}
}
