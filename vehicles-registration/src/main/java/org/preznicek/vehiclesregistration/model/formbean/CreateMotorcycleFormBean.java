package org.preznicek.vehiclesregistration.model.formbean;

public class CreateMotorcycleFormBean extends CreateFormBean {

	private MotorcycleFormBean motorcycle;
	
	public CreateMotorcycleFormBean() {
		super();
		motorcycle = new MotorcycleFormBean();
	}

	public MotorcycleFormBean getMotorcycle() {
		return motorcycle;
	}
	public void setMotorcycle(MotorcycleFormBean motorcycle) {
		this.motorcycle = motorcycle;
	}

	@Override
	public String toString() {
		return super.toString() + " CreateMotorcycleFormBean [motorcycle=" + motorcycle + "]";
	}
}
