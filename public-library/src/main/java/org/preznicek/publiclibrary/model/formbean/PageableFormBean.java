package org.preznicek.publiclibrary.model.formbean;

/**
 * Slouzi pro formulare, jejich vysledky jsou strankovane.
 */
public class PageableFormBean extends BaseFormBean {

	protected String page;

	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
}
