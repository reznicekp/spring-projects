package org.preznicek.vehiclesregistration.database.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Vehicle;

@Entity
public class Owner {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="birth_certificate_number", length=11, nullable=false)
	private String birthCertificateNumber;	// rodne cislo
	
	@Column(length=50, nullable=false)
	private String firstname;
	
	@Column(length=50, nullable=false)
	private String lastname;
	
	@Column(nullable=false)
	@Lob
	private String address;
	
	@Column(length=20, nullable=false)
	private String phone1;
	
	@Column(length=20)
	private String phone2;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL, mappedBy="owner")
	private List<Vehicle> vehicleList;
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}
	public void setVehicleList(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}
}
