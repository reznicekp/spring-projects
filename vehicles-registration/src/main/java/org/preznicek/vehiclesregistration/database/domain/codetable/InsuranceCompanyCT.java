package org.preznicek.vehiclesregistration.database.domain.codetable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ct_insurance_company")
public class InsuranceCompanyCT extends CodeTable {

	public InsuranceCompanyCT() {
		super();
	}
	
	public InsuranceCompanyCT(Integer code, String value, String description) {
		super(code, value, description);
	}
}
