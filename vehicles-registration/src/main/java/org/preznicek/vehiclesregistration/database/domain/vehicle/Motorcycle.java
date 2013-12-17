package org.preznicek.vehiclesregistration.database.domain.vehicle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="vehicle_motorcycle")
public class Motorcycle extends Vehicle {

	@Column(nullable=false)
	private Integer volume;
	
	
	
	
	
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
}
