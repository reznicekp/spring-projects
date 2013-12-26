package org.preznicek.vehiclesregistration.database.domain.vehicle;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.preznicek.vehiclesregistration.database.domain.Insurance;
import org.preznicek.vehiclesregistration.database.domain.Owner;
import org.preznicek.vehiclesregistration.database.domain.codetable.BrandCarCT;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Vehicle {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="vehicle_type", length=10, nullable=false)
	private String vehicleType;
	
	@Column(name="plate_number", length=9, nullable=false)
	private String plateNumber;	// SPZ
	
	@Column(name="other_brand_name", length=50)
	private String otherBrandName;
	
	@Column(length=50, nullable=false)
	private String model;
	
	@Column(name="making_year", nullable=false)
	private Integer makingYear;
	
	@Column(name="mot_end")
	@Temporal(TemporalType.DATE)
	private Date motEnd;	// Ministry of Transport (STK)
	
	private Integer weight;
	
	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	private Owner owner;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL, mappedBy="vehicle")//, fetch=FetchType.EAGER)	// nahrazeno @LazyCollection (JPA @OneToMany nepodporuje eager nacitani vice kolekci najednou, zatimco Hibernate @LazyCollection ano - zde je eager nacitana jeste owner.vehicleList)
	private List<Insurance> insuranceList;
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Integer getMakingYear() {
		return makingYear;
	}
	public void setMakingYear(Integer makingYear) {
		this.makingYear = makingYear;
	}
	public Date getMotEnd() {
		return motEnd;
	}
	public void setMotEnd(Date motEnd) {
		this.motEnd = motEnd;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public List<Insurance> getInsuranceList() {
		return insuranceList;
	}
	public void setInsuranceList(List<Insurance> insuranceList) {
		this.insuranceList = insuranceList;
	}
}
