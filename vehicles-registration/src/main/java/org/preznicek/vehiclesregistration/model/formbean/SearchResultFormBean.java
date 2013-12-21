package org.preznicek.vehiclesregistration.model.formbean;

public class SearchResultFormBean {

	private String id;
	private String vehicleType;
	private String plateNumber;
	private String brandAndModel;
	private String makingYear;
	private String motEnd;
	private String owner;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getBrandAndModel() {
		return brandAndModel;
	}
	public void setBrandAndModel(String brandAndModel) {
		this.brandAndModel = brandAndModel;
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
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString() {
		return "SearchResultFormBean [id=" + id + ", vehicleType="
				+ vehicleType + ", plateNumber=" + plateNumber
				+ ", brandAndModel=" + brandAndModel + ", makingYear="
				+ makingYear + ", motEnd=" + motEnd + ", owner=" + owner + "]";
	}
}
