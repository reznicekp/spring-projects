package org.preznicek.vehiclesregistration.model.formbean;

public class VehicleFormBean {
	
	private String id;
	private String plateNumber;
	private String brand;
	private String otherBrandName;
	private String model;
	private String makingYear;
	private String motEnd;
	private String weight;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getOtherBrandName() {
		return otherBrandName;
	}
	public void setOtherBrandName(String otherBrandName) {
		this.otherBrandName = otherBrandName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMakingYear() {
		return makingYear;
	}
	public void setMakingYear(String makingYear) {
		this.makingYear = makingYear;
	}
	public String getMotEnd() {
		return motEnd;
	}
	public void setMotEnd(String motEnd) {
		this.motEnd = motEnd;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}

	
	@Override
	public String toString() {
		return "VehicleFormBean [id=" + id + ", plateNumber=" + plateNumber
				+ ", brand=" + brand + ", otherBrandName=" + otherBrandName
				+ ", model=" + model + ", makingYear=" + makingYear
				+ ", motEnd=" + motEnd + ", weight=" + weight + "]";
	}
}
