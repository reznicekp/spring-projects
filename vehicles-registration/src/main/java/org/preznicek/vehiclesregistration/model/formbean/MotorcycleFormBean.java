package org.preznicek.vehiclesregistration.model.formbean;

public class MotorcycleFormBean {

	private String volume;
	
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	@Override
	public String toString() {
		return "MotorcycleFormBean [volume=" + volume + "]";
	}
}
