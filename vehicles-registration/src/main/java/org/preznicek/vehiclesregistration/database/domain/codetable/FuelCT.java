package org.preznicek.vehiclesregistration.database.domain.codetable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ct_fuel")
public class FuelCT extends CodeTable {

	//PETROL, DIESEL, CNG, ELECTRIC, HYBRID
	public FuelCT() {
		super();
	}
	
	public FuelCT(Integer code, String value, String description) {
		super(code, value, description);
	}
}
