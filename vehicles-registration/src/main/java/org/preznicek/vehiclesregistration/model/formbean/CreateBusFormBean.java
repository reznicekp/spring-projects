package org.preznicek.vehiclesregistration.model.formbean;

public class CreateBusFormBean extends CreateFormBean {

	private BusFormBean bus;
	
	public CreateBusFormBean() {
		super();
		bus = new BusFormBean();
	}

	public BusFormBean getBus() {
		return bus;
	}
	public void setBus(BusFormBean bus) {
		this.bus = bus;
	}

	@Override
	public String toString() {
		return super.toString() + " CreateBusFormBean [bus=" + bus + "]";
	}
}
