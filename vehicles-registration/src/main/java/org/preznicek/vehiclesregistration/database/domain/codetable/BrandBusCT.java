package org.preznicek.vehiclesregistration.database.domain.codetable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ct_brand_bus")
public class BrandBusCT extends CodeTable {

	public BrandBusCT() {
		super();
	}
	
	public BrandBusCT(Integer code, String value, String description) {
		super(code, value, description);
	}
}
