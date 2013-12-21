package org.preznicek.vehiclesregistration.model.formbean;

public class TruckFormBean {

	private String volume;
	private String bodywork;
	
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getBodywork() {
		return bodywork;
	}
	public void setBodywork(String bodywork) {
		this.bodywork = bodywork;
	}
	
	@Override
	public String toString() {
		return "TruckFormBean [volume=" + volume + ", bodywork=" + bodywork + "]";
	}
}
