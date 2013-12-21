package org.preznicek.vehiclesregistration.model.formbean;

public class InsuranceFormBean {
	
	private String companyCode;
	private String otherCompanyName;
	private String activeFrom;
	private String activeTo;
	
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getOtherCompanyName() {
		return otherCompanyName;
	}
	public void setOtherCompanyName(String otherCompanyName) {
		this.otherCompanyName = otherCompanyName;
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
		return "InsuranceFormBean [companyCode=" + companyCode
				+ ", otherCompanyName=" + otherCompanyName + ", activeFrom="
				+ activeFrom + ", activeTo=" + activeTo + "]";
	}
}
