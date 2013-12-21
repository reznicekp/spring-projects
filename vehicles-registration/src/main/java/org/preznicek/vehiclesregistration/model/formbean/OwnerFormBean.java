package org.preznicek.vehiclesregistration.model.formbean;

import java.util.ArrayList;
import java.util.List;

public class OwnerFormBean {

	private String id;
	private String birthCertificateNumber;
	private String firstname;
	private String lastname;
	private String address;
	private String phone1;
	private String phone2;
	private List<SearchResultFormBean> vehiclesOfOwner;
	
	public OwnerFormBean() {
		vehiclesOfOwner = new ArrayList<SearchResultFormBean>();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBirthCertificateNumber() {
		return birthCertificateNumber;
	}
	public void setBirthCertificateNumber(String birthCertificateNumber) {
		this.birthCertificateNumber = birthCertificateNumber;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public List<SearchResultFormBean> getVehiclesOfOwner() {
		return vehiclesOfOwner;
	}
	public void setVehiclesOfOwner(List<SearchResultFormBean> vehiclesOfOwner) {
		this.vehiclesOfOwner = vehiclesOfOwner;
	}
	
	@Override
	public String toString() {
		return "OwnerFormBean [id=" + id + ", birthCertificateNumber="
				+ birthCertificateNumber + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", address=" + address
				+ ", phone1=" + phone1 + ", phone2=" + phone2
				+ ", vehiclesOfOwner=" + vehiclesOfOwner + "]";
	}
}
