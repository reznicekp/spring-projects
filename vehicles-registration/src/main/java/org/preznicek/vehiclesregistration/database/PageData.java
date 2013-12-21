package org.preznicek.vehiclesregistration.database;

import java.util.List;

/**
 * Instance teto tridy obsahuje seznam instanci objektu libovolne tridy zobrazenych na jedne strance 
 * (z hlediska strankovani) a celkovy pocet techto objektu v databazi (zobrazenych, kdyby strankovani 
 * nebylo zavedeno).
 */
public class PageData {

	private List<?> items;
	private int totalCount;
	
	public PageData(List<?> items, int totalCount) {
		this.items = items;
		this.totalCount = totalCount;
	}
	
	public List<?> getItems() {
		return items;
	}
	public void setItems(List<?> books) {
		this.items = books;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
