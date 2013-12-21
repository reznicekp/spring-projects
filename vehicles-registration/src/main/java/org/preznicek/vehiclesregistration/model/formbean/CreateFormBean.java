package org.preznicek.vehiclesregistration.model.formbean;

import java.util.ArrayList;
import java.util.List;

public class CreateFormBean extends BaseFormBean {

	private VehicleFormBean vehicle;
	private OwnerFormBean owner;
	private InsuranceFormBean insurance;
	private List<InsuranceItemInListFormBean> insuranceList;
	
	public CreateFormBean() {
		vehicle = new VehicleFormBean();
		owner = new OwnerFormBean();
		insurance = new InsuranceFormBean();
		insuranceList = new ArrayList<InsuranceItemInListFormBean>();
	}
	
	public VehicleFormBean getVehicle() {
		return vehicle;
	}
	public void setVehicle(VehicleFormBean vehicle) {
		this.vehicle = vehicle;
	}
	public void setInsuranceList(List<InsuranceItemInListFormBean> insuranceList) {
		this.insuranceList = insuranceList;
	}
	public InsuranceFormBean getInsurance() {
		return insurance;
	}
	public void setInsurance(InsuranceFormBean insurance) {
		this.insurance = insurance;
	}
	public List<InsuranceItemInListFormBean> getInsuranceList() {
		return insuranceList;
	}
	@SuppressWarnings("hiding")
	public void InsuranceItemInListFormBean(List<InsuranceItemInListFormBean> insuranceList) {
		this.insuranceList = insuranceList;
	}
	public OwnerFormBean getOwner() {
		return owner;
	}
	public void setOwner(OwnerFormBean owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString() {
		return "CreateFormBean [vehicle=" + vehicle + ", owner=" + owner
				+ ", insurance=" + insurance + ", insuranceList="
				+ insuranceList + "]";
	}
}
