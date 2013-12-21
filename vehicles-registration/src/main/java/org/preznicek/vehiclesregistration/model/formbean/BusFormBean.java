package org.preznicek.vehiclesregistration.model.formbean;

public class BusFormBean {

	private String bodywork;
	private String sittingPlacesCount;
	private String volume;
	
	public String getBodywork() {
		return bodywork;
	}
	public void setBodywork(String bodywork) {
		this.bodywork = bodywork;
	}
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
	
	@Override
	public String toString() {
		return "BusFormBean [bodywork=" + bodywork + ", sittingPlacesCount="
				+ sittingPlacesCount + ", volume=" + volume + "]";
	}
}
