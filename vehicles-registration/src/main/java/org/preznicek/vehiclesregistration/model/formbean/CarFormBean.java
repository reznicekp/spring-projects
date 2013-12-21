package org.preznicek.vehiclesregistration.model.formbean;

public class CarFormBean {

	private String sittingPlacesCount;
	private String volume;
	private String fuel;
	
	public String getSittingPlacesCount() {
		return sittingPlacesCount;
	}
	public void setSittingPlacesCount(String sittingPlacesCount) {
		this.sittingPlacesCount = sittingPlacesCount;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	
	@Override
	public String toString() {
		return "CarFormBean [sittingPlacesCount=" + sittingPlacesCount
				+ ", volume=" + volume + ", fuel=" + fuel + "]";
	}
}
