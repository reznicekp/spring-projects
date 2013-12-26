package org.preznicek.vehiclesregistration.database.domain.vehicle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.preznicek.vehiclesregistration.database.domain.codetable.BrandMotorcycleCT;

@Entity
@Table(name="vehicle_motorcycle")
public class Motorcycle extends Vehicle {

	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	private BrandMotorcycleCT brand;
	
	@Column(nullable=false)
	private Integer volume;
	
	
	
	
	
	public BrandMotorcycleCT getBrand() {
		return brand;
	}
	public void setBrand(BrandMotorcycleCT brand) {
		this.brand = brand;
	}
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
}
