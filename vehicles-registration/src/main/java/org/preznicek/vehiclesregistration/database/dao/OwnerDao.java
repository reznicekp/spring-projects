package org.preznicek.vehiclesregistration.database.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.preznicek.vehiclesregistration.database.domain.Owner;
import org.springframework.stereotype.Repository;

@Repository
public class OwnerDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public Owner getOwnerByBCN(String birthCertificateNumber) {
		getSession();
		
		Criteria criteria = session.createCriteria(Owner.class);
		criteria.add(Restrictions.eq("birthCertificateNumber", birthCertificateNumber));
		List<Owner> list = criteria.list();
		
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
