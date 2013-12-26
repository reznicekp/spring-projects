package org.preznicek.vehiclesregistration.database.domain.vehicle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.preznicek.vehiclesregistration.database.domain.codetable.BrandCarCT;
import org.preznicek.vehiclesregistration.database.domain.codetable.FuelCT;

@Entity
@Table(name="vehicle_car")
public class Car extends Vehicle {

	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	private BrandCarCT brand;
	
	@Column(name="sitting_places_count", nullable=false)
	private Integer sittingPlacesCount;
	
	@Column(nullable=false)
	private Integer volume;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	private FuelCT fuel;
	
	
	
	
	
	public BrandCarCT getBrand() {
		return brand;
	}
	public void setBrand(BrandCarCT brand) {
		this.brand = brand;
	}
	public Integer getSittingPlacesCount() {
		return sittingPlacesCount;
	}
	public void setSittingPlacesCount(Integer sittingPlacesCount) {
		this.sittingPlacesCount = sittingPlacesCount;
	}
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	public FuelCT getFuel() {
		return fuel;
	}
	public void setFuel(FuelCT fuel) {
		this.fuel = fuel;
	}
}
