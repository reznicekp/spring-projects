package org.preznicek.publiclibrary.model.formbean;

public class SearchFormBean extends PageableFormBean {

	private String searchedSubject;

	public String getSearchedSubject() {
		return searchedSubject;
	}
	public void setSearchedSubject(String searchedSubject) {
		this.searchedSubject = searchedSubject;
	}
	
	@Override
	public String toString() {
		return "SearchFormBean [searchedSubject=" + searchedSubject + ", page="
				+ page + "]";
	}
}
