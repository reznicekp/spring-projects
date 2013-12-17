package org.preznicek.vehiclesregistration.database.domain.codetable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ct_bodywork_truck")
public class BodyworkTruckCT extends CodeTable {

	//TRAILER/*TAHAC*/, FLATBED/*VALNIK*/, TIPPER/*SKLAPEC*/, CASE/*SKRIN*/, IZOTHERM, TANKER/*CISTERNA*/, SPECIAL;
	public BodyworkTruckCT() {
		super();
	}
	
	public BodyworkTruckCT(Integer code, String value, String description) {
		super(code, value, description);
	}
}
