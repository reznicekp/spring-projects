package org.preznicek.vehiclesregistration.model.formbean;

public class InsuranceItemInListFormBean {
	
	private String id;
	private String order;
	private String companyName;
	private String activeFrom;
	private String activeTo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getActiveFrom() {
		return activeFrom;
	}
	public void setActiveFrom(String activeFrom) {
		this.activeFrom = activeFrom;
	}
	public String getActiveTo() {
		return activeTo;
	}
	public void setActiveTo(String activeTo) {
		this.activeTo = activeTo;
	}
	
	@Override
	public String toString() {
		return "InsuranceItemInListFormBean [id=" + id + ", order=" + order
				+ ", companyName=" + companyName + ", activeFrom=" + activeFrom
				+ ", activeTo=" + activeTo + "]";
	}
}
