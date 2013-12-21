package org.preznicek.vehiclesregistration.model.formbean;

import java.util.List;

public class SearchFormBean extends BaseFormBean {

	private SearchVehicleFormBean searchVehicleFormBean;
	private SearchOwnerFormBean searchOwnerFormBean;
	private List<SearchResultFormBean> searchResultFormBeanList;
	private Integer searchResultFormBeanList_page;
	
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
	public void setSearchResultFormBeanList(List<SearchResultFormBean> searchResultFormBeanList) {
		this.searchResultFormBeanList = searchResultFormBeanList;
	}
	public Integer getSearchResultFormBeanList_page() {
		return searchResultFormBeanList_page;
	}
	public void setSearchResultFormBeanList_page(Integer searchResultFormBeanList_page) {
		this.searchResultFormBeanList_page = searchResultFormBeanList_page;
	}
	
	@Override
	public String toString() {
		return "SearchFormBean [searchVehicleFormBean=" + searchVehicleFormBean
				+ ", searchOwnerFormBean=" + searchOwnerFormBean
				+ ", searchResultFormBeanList=" + searchResultFormBeanList
				+ ", searchResultFormBeanList_page="
				+ searchResultFormBeanList_page + "]";
	}
}
