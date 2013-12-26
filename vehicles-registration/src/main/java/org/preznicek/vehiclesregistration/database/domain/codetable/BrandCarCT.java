package org.preznicek.vehiclesregistration.database.domain.codetable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ct_brand_car")
public class BrandCarCT extends CodeTable {

	public BrandCarCT() {
		super();
	}
	
	public BrandCarCT(Integer code, String value, String description) {
		super(code, value, description);
	}
}
