package org.preznicek.publiclibrary.model.formbean;

public class RegistrationFormBean extends BaseFormBean {

	private String firstname;
	private String lastname;
	private String email;
	private String birthYear;
	private String gpsLatitude;
	private String gpsLongitude;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
	public String getGpsLatitude() {
		return gpsLatitude;
	}
	public void setGpsLatitude(String gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}
	public String getGpsLongitude() {
		return gpsLongitude;
	}
	public void setGpsLongitude(String gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}
	
	@Override
	public String toString() {
		return "RegistrationFormBean [firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", birthYear=" + birthYear
				+ ", gpsLatitude=" + gpsLatitude + ", gpsLongitude="
				+ gpsLongitude + "]";
	}
}
