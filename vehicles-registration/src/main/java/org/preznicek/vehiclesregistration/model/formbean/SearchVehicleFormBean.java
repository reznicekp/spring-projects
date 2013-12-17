package org.preznicek.vehiclesregistration.model.formbean;

public class SearchVehicleFormBean {
	
	private String plateNumber;
	private String brand;
	private String model;
	private String makingYearFrom;
	private String makingYearTo;
	private String motEndFrom;
	private String motEndTo;
	private String insuranceCompany;
	private String onlyActiveInsurance;
	
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMakingYearFrom() {
		return makingYearFrom;
	}
	public void setMakingYearFrom(String makingYearFrom) {
		this.makingYearFrom = makingYearFrom;
	}
	public String getMakingYearTo() {
		return makingYearTo;
	}
	public void setMakingYearTo(String makingYearTo) {
		this.makingYearTo = makingYearTo;
	}
	public String getMotEndFrom() {
		return motEndFrom;
	}
	public void setMotEndFrom(String motEndFrom) {
		this.motEndFrom = motEndFrom;
	}
	public String getMotEndTo() {
		return motEndTo;
	}
	public void setMotEndTo(String motEndTo) {
		this.motEndTo = motEndTo;
	}
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	public String getOnlyActiveInsurance() {
		return onlyActiveInsurance;
	}
	public void setOnlyActiveInsurance(String onlyActiveInsurance) {
		this.onlyActiveInsurance = onlyActiveInsurance;
	}
	
	@Override
	public String toString() {
		return "SearchVehicleFormBean [plateNumber=" + plateNumber + ", brand="
				+ brand + ", model=" + model + ", makingYearFrom="
				+ makingYearFrom + ", makingYearTo=" + makingYearTo
				+ ", motEndFrom=" + motEndFrom + ", motEndTo=" + motEndTo
				+ ", insuranceCompany=" + insuranceCompany
				+ ", onlyActiveInsurance=" + onlyActiveInsurance + "]";
	}
}
