package org.preznicek.vehiclesregistration.model.formbean;

public class CreateTruckFormBean extends CreateFormBean {

	private TruckFormBean truck;
	
	public CreateTruckFormBean() {
		super();
		truck = new TruckFormBean();
	}

	public TruckFormBean getTruck() {
		return truck;
	}
	public void setTruck(TruckFormBean car) {
		this.truck = car;
	}

	@Override
	public String toString() {
		return super.toString() + " CreateTruckFormBean [truck=" + truck + "]";
	}
}
