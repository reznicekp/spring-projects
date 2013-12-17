package org.preznicek.vehiclesregistration.model.formbean;

public class CreateCarFormBean extends CreateFormBean {

	private CarFormBean car;
	
	public CreateCarFormBean() {
		super();
		car = new CarFormBean();
	}

	public CarFormBean getCar() {
		return car;
	}
	public void setCar(CarFormBean car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return super.toString() + " CreateCarFormBean [car=" + car + "]";
	}
}
