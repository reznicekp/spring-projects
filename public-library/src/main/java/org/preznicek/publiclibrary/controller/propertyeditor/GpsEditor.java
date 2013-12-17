package org.preznicek.publiclibrary.controller.propertyeditor;

import java.beans.PropertyEditorSupport;

public class GpsEditor extends PropertyEditorSupport {

	/**
	 * Zpetne vykresleni do formulare - <code>null</code> se nahradi prazdnym retezcem
	 * a carka se nahradi teckou.
	 */
	@Override
	public String getAsText() {
		String text = super.getAsText();
		if ("null".equals(text)) {
			text = "";
		}
		return text.replaceAll(",", ".");
	}
	
	/**
	 * Uzivatel muze na formulari naspat tecku i carku a zde se dany symbol zkonvertuje na carku,
	 * takze ve vysledku jde do validace a dalsiho procesu v kazdem pripade carka.
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(text.replaceAll("\\.", ","));
	}
}
