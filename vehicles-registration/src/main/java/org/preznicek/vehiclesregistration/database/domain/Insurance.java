package org.preznicek.vehiclesregistration.database.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.preznicek.vehiclesregistration.database.domain.codetable.InsuranceCompanyCT;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Vehicle;

@Entity
@Table(name="insurance")
public class Insurance {

	@Id
	@GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="active_from", nullable=false)
	private Date activeFrom;
	
	@Temporal(TemporalType.DATE)
	@Column(name="active_to", nullable=false)
	private Date activeTo;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name="insurance_company_code")
	private InsuranceCompanyCT insuranceCompany;
	
	@Column(name="other_insurance_company_name", length=50)
	private String otherInsuranceCompanyName;
	
	@ManyToOne(optional=false)
	private Vehicle vehicle;
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getActiveFrom() {
		return activeFrom;
	}
	public void setActiveFrom(Date activeFrom) {
		this.activeFrom = activeFrom;
	}
	public Date getActiveTo() {
		return activeTo;
	}
	public void setActiveTo(Date activeTo) {
		this.activeTo = activeTo;
	}
	public InsuranceCompanyCT getInsuranceCompany() {
		return insuranceCompany;
	}
	public void setInsuranceCompany(InsuranceCompanyCT insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	public String getOtherInsuranceCompanyName() {
		return otherInsuranceCompanyName;
	}
	public void setOtherInsuranceCompanyName(String otherInsuranceCompanyName) {
		this.otherInsuranceCompanyName = otherInsuranceCompanyName;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
