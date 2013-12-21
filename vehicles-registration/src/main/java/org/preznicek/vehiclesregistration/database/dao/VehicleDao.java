package org.preznicek.vehiclesregistration.database.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.preznicek.vehiclesregistration.database.PageData;
import org.preznicek.vehiclesregistration.database.domain.Owner;
import org.preznicek.vehiclesregistration.database.domain.codetable.BrandCT;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Vehicle;
import org.preznicek.vehiclesregistration.model.formbean.SearchFormBean;
import org.preznicek.vehiclesregistration.utils.Constants;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class VehicleDao extends BaseDao {

	public void upsert(Vehicle vehicle) {
		getSession();
		
		session.saveOrUpdate(vehicle);
	}
	
	@SuppressWarnings("unchecked")
	public PageData getVehicleList(SearchFormBean searchFormBean, int pageNumber) throws ParseException {
		getSession();
		
		Criteria criteria = session.createCriteria(Vehicle.class, "v")
									.createAlias("owner", "o")
									.createAlias("brand", "b", CriteriaSpecification.LEFT_JOIN)
									.createCriteria("insuranceList", "i", CriteriaSpecification.LEFT_JOIN)
									.createCriteria("insuranceCompany", "c", CriteriaSpecification.LEFT_JOIN);
		criteria.setProjection(Projections.distinct(Projections.projectionList()
				.add(Projections.property("v.id"))
				.add(Projections.property("v.vehicleType"))
				.add(Projections.property("v.plateNumber"))
				.add(Projections.property("b.value"))
				.add(Projections.property("v.otherBrandName"))
				.add(Projections.property("v.model"))
				.add(Projections.property("v.makingYear"))
				.add(Projections.property("v.motEnd"))
				.add(Projections.property("o.firstname"))
				.add(Projections.property("o.lastname"))
//				.add(Projections.property("i.otherInsuranceCompanyName"))
//				.add(Projections.property("c.value"))
		));
		criteria.addOrder(Order.asc("o.lastname"));
		
		if (searchFormBean.getSearchVehicleFormBean() != null) {
			if (!StringUtils.isEmpty(searchFormBean.getSearchVehicleFormBean().getPlateNumber())) {
				criteria.add(Restrictions.ilike("v.plateNumber", searchFormBean.getSearchVehicleFormBean().getPlateNumber(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchVehicleFormBean().getBrand())) {
				criteria.add(Restrictions.or(Restrictions.ilike("b.value", searchFormBean.getSearchVehicleFormBean().getBrand(), MatchMode.ANYWHERE), Restrictions.ilike("v.otherBrandName", searchFormBean.getSearchVehicleFormBean().getBrand(), MatchMode.ANYWHERE)));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchVehicleFormBean().getModel())) {
				criteria.add(Restrictions.ilike("v.model", searchFormBean.getSearchVehicleFormBean().getModel(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchVehicleFormBean().getMakingYearFrom())) {
				criteria.add(Restrictions.ge("v.makingYear", Integer.valueOf(searchFormBean.getSearchVehicleFormBean().getMakingYearFrom())));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchVehicleFormBean().getMakingYearTo())) {
				criteria.add(Restrictions.le("v.makingYear", Integer.valueOf(searchFormBean.getSearchVehicleFormBean().getMakingYearTo())));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchVehicleFormBean().getMotEndFrom())) {
				criteria.add(Restrictions.ge("v.motEnd", new SimpleDateFormat(Constants.MOT_DATE_FORMAT).parse(searchFormBean.getSearchVehicleFormBean().getMotEndFrom())));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchVehicleFormBean().getMotEndTo())) {
				criteria.add(Restrictions.le("v.motEnd", new SimpleDateFormat(Constants.MOT_DATE_FORMAT).parse(searchFormBean.getSearchVehicleFormBean().getMotEndTo())));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchVehicleFormBean().getInsuranceCompany())) {
				criteria.add(Restrictions.or(Restrictions.ilike("c.value", searchFormBean.getSearchVehicleFormBean().getInsuranceCompany(), MatchMode.ANYWHERE), Restrictions.ilike("i.otherInsuranceCompanyName", searchFormBean.getSearchVehicleFormBean().getInsuranceCompany(), MatchMode.ANYWHERE)));
				if (searchFormBean.getSearchVehicleFormBean().getOnlyActiveInsurance() != null) {		// zaskrtnuto - "", odskrtnuto - null
					criteria.add(Restrictions.and(Restrictions.le("i.activeFrom", new Date()), Restrictions.ge("i.activeTo", new Date())));
				}
			}
		}
		if (searchFormBean.getSearchOwnerFormBean() != null) {
			if (!StringUtils.isEmpty(searchFormBean.getSearchOwnerFormBean().getFirstname())) {
				criteria.add(Restrictions.ilike("o.firstname", searchFormBean.getSearchOwnerFormBean().getFirstname(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchOwnerFormBean().getLastname())) {
				criteria.add(Restrictions.ilike("o.lastname", searchFormBean.getSearchOwnerFormBean().getLastname(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchOwnerFormBean().getBirthCertificateNumber())) {
				criteria.add(Restrictions.ilike("o.birthCertificateNumber", searchFormBean.getSearchOwnerFormBean().getBirthCertificateNumber(), MatchMode.ANYWHERE));
			}
		}
		
		int count = criteria.list().size();
		
		criteria.setFirstResult((pageNumber - 1) * Constants.PAGE_SIZE);
		criteria.setMaxResults(Constants.PAGE_SIZE);
		
		List<Object> objectList = criteria.list();
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		
		for (Object object : objectList) {
			Object[] row = (Object[]) object;
			
			Vehicle vehicle = new Vehicle();
			vehicle.setId((Long) row[0]);
			vehicle.setVehicleType((String) row[1]);
			vehicle.setPlateNumber((String) row[2]);
			vehicle.setBrand(new BrandCT(null, (String) row[3], null));
			vehicle.setOtherBrandName((String) row[4]);
			vehicle.setModel((String) row[5]);
			vehicle.setMakingYear((Integer) row[6]);
			vehicle.setMotEnd((Date) row[7]);
			
			Owner owner = new Owner();
			owner.setFirstname((String) row[8]);
			owner.setLastname((String) row[9]);
			vehicle.setOwner(owner);
			
			vehicleList.add(vehicle);
		}
		
		return new PageData(vehicleList, count);
	}
	
	public Vehicle getVehicleById(Long id, Class<? extends Vehicle> clazz) {
		getSession();
		
		Vehicle vehicle = (Vehicle) session.get(clazz, id);
		
		return vehicle;
	}
	
	/**
	 * NEPOUZIVA SE - bylo nahrazeno eager nacitanim.
	 * @param ownerId	ID majitele vozidel.
	 * @return	Seznam vozidel daneho majitele.
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	public List<Vehicle> getVehicleListByOwnerId(Long ownerId) {
		getSession();
		
		Criteria criteria = session.createCriteria(Vehicle.class, "v")
									.createCriteria("brand", "b");
		criteria.setProjection(Projections.distinct(Projections.projectionList()
				.add(Projections.property("v.id"))
				.add(Projections.property("v.vehicleType"))
				.add(Projections.property("v.plateNumber"))
				.add(Projections.property("b.value"))
				.add(Projections.property("v.otherBrandName"))
				.add(Projections.property("v.model"))
				.add(Projections.property("v.makingYear"))
				.add(Projections.property("v.motEnd"))
		));
		criteria.add(Restrictions.eq("v.owner.id", ownerId));
		
		List<Object> objectList = criteria.list();
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		
		for (Object object : objectList) {
			Object[] row = (Object[]) object;
			
			Vehicle vehicle = new Vehicle();
			vehicle.setId((Long) row[0]);
			vehicle.setVehicleType((String) row[1]);
			vehicle.setPlateNumber((String) row[2]);
			vehicle.setBrand(new BrandCT(null, (String) row[3], null));
			vehicle.setOtherBrandName((String) row[4]);
			vehicle.setModel((String) row[5]);
			vehicle.setMakingYear((Integer) row[6]);
			vehicle.setMotEnd((Date) row[7]);
			
			vehicleList.add(vehicle);
		}
		
		return vehicleList;
	}
}
