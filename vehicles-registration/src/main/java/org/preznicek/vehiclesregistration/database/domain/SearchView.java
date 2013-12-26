package org.preznicek.vehiclesregistration.database.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * http://timezra.blogspot.cz/2009/05/mapping-hibernate-entities-to-views.html
 * View:
create or replace view vw_search as 
select distinct 
	veh.id vehicle_id, veh.vehicle_type, veh.plate_number vehicle_plate_number, ifnull(ifnull(ctb.value, ifnull(ctc.value, ifnull(ctm.value, ctt.value))), veh.other_brand_name) vehicle_brand, veh.model vehicle_model, veh.making_year vehicle_making_year, veh.mot_end vehicle_mot_end, own.firstname owner_firstname, own.lastname owner_lastname, ins.active_from insurance_active_from, ins.active_to insurance_active_to, ifnull(com.value, ins.other_insurance_company_name) insurance_company 
from Vehicle veh 
	left outer join vehicle_bus bus on veh.id=bus.id 
	left outer join vehicle_car car on veh.id=car.id 
	left outer join vehicle_motorcycle mot on veh.id=mot.id 
	left outer join vehicle_truck tru on veh.id=tru.id 
	left outer join ct_brand_bus ctb on bus.brand_code=ctb.code 
	left outer join ct_brand_car ctc on car.brand_code=ctc.code 
	left outer join ct_brand_motorcycle ctm on mot.brand_code=ctm.code 
	left outer join ct_brand_truck ctt on tru.brand_code=ctt.code 
	left outer join insurance ins on veh.id=ins.vehicle_id 
	left outer join ct_insurance_company com on ins.insurance_company_code=com.code 
	inner join owner own on veh.owner_id=own.id 
order by own.lastname asc;
 */
@Entity
@Table(name="vw_search")
public class SearchView {

	@Id
	@Column(name="vehicle_id", insertable=false, updatable=false)
	private Long vehicleId;
	
	@Column(name="vehicle_type", insertable=false, updatable=false)
	private String vehicleType;
	
	@Column(name="vehicle_plate_number", insertable=false, updatable=false)
	private String vehiclePlateNumber;
	
	@Column(name="vehicle_brand", insertable=false, updatable=false)
	private String vehicleBrand;
	
	@Column(name="vehicle_model", insertable=false, updatable=false)
	private String vehicleModel;
	
	@Column(name="vehicle_making_year", insertable=false, updatable=false)
	private Integer vehicleMakingYear;
	
	@Column(name="vehicle_mot_end", insertable=false, updatable=false)
	private Date vehicleMotEnd;
	
	@Column(name="owner_firstname", insertable=false, updatable=false)
	private String ownerFirstname;
	
	@Column(name="owner_lastname", insertable=false, updatable=false)
	private String ownerLastname;
	
	@Column(name="owner_birth_certificate_number", insertable=false, updatable=false)
	private String ownerBirthCertificateNumber;
	
	@Column(name="insurance_company", insertable=false, updatable=false)
	private String insuranceCompany;
	
	@Column(name="insurance_active_from", insertable=false, updatable=false)
	private Date insuranceActiveFrom;
	
	@Column(name="insurance_active_to", insertable=false, updatable=false)
	private Date insuranceActiveTo;
	
	
	
	
	
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getVehiclePlateNumber() {
		return vehiclePlateNumber;
	}
	public void setVehiclePlateNumber(String vehiclePlateNumber) {
		this.vehiclePlateNumber = vehiclePlateNumber;
	}
	public String getVehicleBrand() {
		return vehicleBrand;
	}
	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}
	public String getVehicleModel() {
		return vehicleModel;
	}
	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}
	public Integer getVehicleMakingYear() {
		return vehicleMakingYear;
	}
	public void setVehicleMakingYear(Integer vehicleMakingYear) {
		this.vehicleMakingYear = vehicleMakingYear;
	}
	public Date getVehicleMotEnd() {
		return vehicleMotEnd;
	}
	public void setVehicleMotEnd(Date vehicleMotEnd) {
		this.vehicleMotEnd = vehicleMotEnd;
	}
	public String getOwnerFirstname() {
		return ownerFirstname;
	}
	public void setOwnerFirstname(String ownerFirstname) {
		this.ownerFirstname = ownerFirstname;
	}
	public String getOwnerLastname() {
		return ownerLastname;
	}
	public void setOwnerLastname(String ownerLastname) {
		this.ownerLastname = ownerLastname;
	}
	public String getOwnerBirthCertificateNumber() {
		return ownerBirthCertificateNumber;
	}
	public void setOwnerBirthCertificateNumber(String ownerBirthCertificateNumber) {
		this.ownerBirthCertificateNumber = ownerBirthCertificateNumber;
	}
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	public Date getInsuranceActiveFrom() {
		return insuranceActiveFrom;
	}
	public void setInsuranceActiveFrom(Date insuranceActiveFrom) {
		this.insuranceActiveFrom = insuranceActiveFrom;
	}
	public Date getInsuranceActiveTo() {
		return insuranceActiveTo;
	}
	public void setInsuranceActiveTo(Date insuranceActiveTo) {
		this.insuranceActiveTo = insuranceActiveTo;
	}
}
