package org.preznicek.vehiclesregistration.database.domain.codetable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ct_bodywork_bus")
public class BodyworkBusCT extends CodeTable {

	//MUNICIPAL, SUBURBAN, LONG_DISTANCE, LIVING;
	public BodyworkBusCT() {
		super();
	}
	
	public BodyworkBusCT(Integer code, String value, String description) {
		super(code, value, description);
	}
}
