package org.preznicek.vehiclesregistration.database.domain.vehicle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.preznicek.vehiclesregistration.database.domain.codetable.BodyworkBusCT;

@Entity
@Table(name="vehicle_bus")
public class Bus extends Vehicle {

	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	private BodyworkBusCT bodywork;
	
	@Column(name="sitting_places_count", nullable=false)
	private Integer sittingPlacesCount;
	
	@Column(nullable=false)
	private Integer volume;
	
	
	
	
	
	public BodyworkBusCT getBodywork() {
		return bodywork;
	}
	public void setBodywork(BodyworkBusCT bodywork) {
		this.bodywork = bodywork;
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
}
