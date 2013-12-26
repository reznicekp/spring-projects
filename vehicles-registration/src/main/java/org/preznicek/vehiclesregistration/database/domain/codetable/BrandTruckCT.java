package org.preznicek.vehiclesregistration.database.domain.codetable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ct_brand_truck")
public class BrandTruckCT extends CodeTable {

	public BrandTruckCT() {
		super();
	}
	
	public BrandTruckCT(Integer code, String value, String description) {
		super(code, value, description);
	}
}
