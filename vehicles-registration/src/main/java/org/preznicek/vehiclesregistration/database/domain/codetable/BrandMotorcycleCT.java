package org.preznicek.vehiclesregistration.database.domain.codetable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ct_brand_motorcycle")
public class BrandMotorcycleCT extends CodeTable {

	public BrandMotorcycleCT() {
		super();
	}
	
	public BrandMotorcycleCT(Integer code, String value, String description) {
		super(code, value, description);
	}
}
