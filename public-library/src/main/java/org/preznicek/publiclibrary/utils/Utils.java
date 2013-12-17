package org.preznicek.publiclibrary.utils;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.context.ApplicationContext;

public class Utils {

	/**
	 * Funkce pro sestaveni radiobuttonu s popisky Yes a No. Yes musi byt na formulari zobrazeno prvni, 
	 * proto je logika invertovana (Yes ma hodnotu 0, No ma hodnotu 1) - <strong>na toto je potreba si
	 * dat pozor pri vyhodnocovani odeslaneho formulare</strong>.
	 * @param context
	 * @return	Polozky, ktere je mozne vlozit do modelu zobrazovane stranky.
	 */
	public static HashMap<Integer, String> getYesNoRadioItems(ApplicationContext context) {
		HashMap<Integer, String> items = new HashMap<Integer, String>();
		items.put(Integer.valueOf(0), context.getMessage("yes", null, Locale.getDefault()));
		items.put(Integer.valueOf(1), context.getMessage("no", null, Locale.getDefault()));
		
		return items;
	}
}
