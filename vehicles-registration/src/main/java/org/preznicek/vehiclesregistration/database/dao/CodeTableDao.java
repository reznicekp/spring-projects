package org.preznicek.vehiclesregistration.database.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.preznicek.vehiclesregistration.database.domain.codetable.CodeTable;
import org.preznicek.vehiclesregistration.database.domain.codetable.ModelCT;
import org.springframework.stereotype.Repository;

@Repository
public class CodeTableDao extends BaseDao {

	/**
	 * Ulozi zaznamy ciselniku do databaze.
	 * @param codeTable		Data ciselniku.
	 */
	public void fillCodeTable(List<? extends CodeTable> codeTable) {
		getSession();
		
		for (CodeTable ct : codeTable) {
			session.saveOrUpdate(ct);
		}
	}
	
	/**
	 * Vrati data z ciselniku.
	 * @param codeTableName		Trida urcujici ciselnik.
	 * @return					Data z ciselniku.
	 */
	@SuppressWarnings("unchecked")
	public List<? extends CodeTable> getCodeTableData(Class<? extends CodeTable> codeTableName) {
		getSession();
		
		return session.createCriteria(codeTableName).list();
	}
	
	/**
	 * Vrati modely dane znacky.
	 * @param brandCode		Kod znacky.
	 * @return				Modely znacky.
	 */
	@SuppressWarnings("unchecked")
	public List<ModelCT> getModelListByBrand(Integer brandCode) {
		getSession();
		
		Criteria criteria = session.createCriteria(ModelCT.class);
		criteria.add(Restrictions.eq("brandCode", brandCode));
		criteria.addOrder(Order.asc("value"));
		
		return criteria.list();
	}
}
