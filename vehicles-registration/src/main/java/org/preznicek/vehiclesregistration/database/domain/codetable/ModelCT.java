package org.preznicek.vehiclesregistration.database.domain.codetable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ct_model")
public class ModelCT extends CodeTable {

	@Column(name="brand_code")
	private Integer brandCode;
	
	public ModelCT() {
		super();
	}
	
	public ModelCT(Integer code, Integer brandCode, String value, String description) {
		super(code, value, description);
		this.brandCode = brandCode;
	}

	public Integer getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(Integer brandCode) {
		this.brandCode = brandCode;
	}
}
