package org.preznicek.vehiclesregistration.database.domain.vehicle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.preznicek.vehiclesregistration.database.domain.codetable.BodyworkTruckCT;

@Entity
@Table(name="vehicle_truck")
public class Truck extends Vehicle {

	@Column(nullable=false)
	private Integer volume;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	private BodyworkTruckCT bodywork;
	
	
	
	
	
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	public BodyworkTruckCT getBodywork() {
		return bodywork;
	}
	public void setBodywork(BodyworkTruckCT bodywork) {
		this.bodywork = bodywork;
	}
}
