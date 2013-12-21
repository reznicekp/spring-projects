package org.preznicek.vehiclesregistration.database.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.preznicek.vehiclesregistration.database.domain.Insurance;
import org.springframework.stereotype.Repository;

@Repository
public class InsuranceDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<Insurance> getInsuranceListByCar(Long idCar) {
		getSession();
		
		Criteria criteria = session.createCriteria(Insurance.class);
		criteria.add(Restrictions.eq("vehicle.id", idCar));
		List<Insurance> insuranceList = criteria.list();
		
		return insuranceList;
	}
	
	public void upsert(Insurance insurance) {
		getSession();
		
		session.saveOrUpdate(insurance);
	}
	
	public Insurance getInsuranceById(Long id) {
		getSession();
		
		Insurance insurance = (Insurance) session.get(Insurance.class, id);
		
		return insurance;
	}
	
	public void delete(Insurance insurance) {
		getSession();
		
		session.delete(insurance);
	}
}
