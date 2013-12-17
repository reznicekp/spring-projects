package org.preznicek.vehiclesregistration.database.domain.codetable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="code_table")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class CodeTable {

	// kazdy ciselnik by mohl mit definovany vlastni SequenceGenerator (s rozdilnou initialValue), ale MySQLDialect tyto sekvence nepodporuje
	@Id
	private Integer code;
	
	@Column(length=50, nullable=false)
	private String value;
	
	private String description;
	
	
	
	
	
	public CodeTable() {
		
	}
	
	public CodeTable(Integer code, String value, String description) {
		this.code = code;
		this.value = value;
		this.description = description;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
