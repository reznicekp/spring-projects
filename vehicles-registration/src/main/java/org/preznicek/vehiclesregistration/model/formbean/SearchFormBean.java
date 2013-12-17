package org.preznicek.vehiclesregistration.model.formbean;

import java.util.List;

public class SearchFormBean extends BaseFormBean {

	private SearchVehicleFormBean searchVehicleFormBean;
	private SearchOwnerFormBean searchOwnerFormBean;
	private List<SearchResultFormBean> searchResultFormBeanList;
	
	public SearchVehicleFormBean getSearchVehicleFormBean() {
		return searchVehicleFormBean;
	}
	public void setSearchVehicleFormBean(SearchVehicleFormBean searchVehicleFormBean) {
		this.searchVehicleFormBean = searchVehicleFormBean;
	}
	public SearchOwnerFormBean getSearchOwnerFormBean() {
		return searchOwnerFormBean;
	}
	public void setSearchOwnerFormBean(SearchOwnerFormBean searchOwnerFormBean) {
		this.searchOwnerFormBean = searchOwnerFormBean;
	}
	public List<SearchResultFormBean> getSearchResultFormBeanList() {
		return searchResultFormBeanList;
	}
	public void setSearchResultFormBeanList(
			List<SearchResultFormBean> searchResultFormBeanList) {
		this.searchResultFormBeanList = searchResultFormBeanList;
	}

	@Override
	public String toString() {
		return "SearchFormBean [searchVehicleFormBean=" + searchVehicleFormBean
				+ ", searchOwnerFormBean=" + searchOwnerFormBean
				+ ", searchResultFormBeanList=" + searchResultFormBeanList
				+ "]";
	}
}
