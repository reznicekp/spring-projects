package org.preznicek.vehiclesregistration.database.dao;

import org.preznicek.vehiclesregistration.database.domain.Insurance;
import org.springframework.stereotype.Repository;

@Repository
public class InsuranceDao extends BaseDao {

	/**
	 * Ulozi zaznam pojisteni.
	 * @param insurance		Pojisteni.
	 */
	public void upsert(Insurance insurance) {
		getSession();
		session.saveOrUpdate(insurance);
	}
	
	/**
	 * Vrati detail pojisteni podle ID. Pouziva se pri jeho odstraneni.
	 * @param id	ID pojisteni.
	 * @return		Detail pojisteni.
	 */
	public Insurance getInsuranceById(Long id) {
		getSession();
		return (Insurance) session.get(Insurance.class, id);
	}
	
	/**
	 * Odstrani zaznam pojisteni z databaze.
	 * @param insurance		Pojisteni.
	 */
	public void delete(Insurance insurance) {
		getSession();
		session.delete(insurance);
	}
}
