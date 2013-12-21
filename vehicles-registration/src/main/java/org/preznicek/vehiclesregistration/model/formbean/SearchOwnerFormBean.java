package org.preznicek.vehiclesregistration.model.formbean;

public class SearchOwnerFormBean {

	private String birthCertificateNumber;
	private String firstname;
	private String lastname;
	
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
	
	@Override
	public String toString() {
		return "SearchOwnerFormBean [birthCertificateNumber="
				+ birthCertificateNumber + ", firstname=" + firstname
				+ ", lastname=" + lastname + "]";
	}
}
