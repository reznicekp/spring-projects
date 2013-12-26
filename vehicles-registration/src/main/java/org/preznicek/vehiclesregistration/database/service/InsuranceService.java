package org.preznicek.vehiclesregistration.database.service;

import org.preznicek.vehiclesregistration.database.dao.InsuranceDao;
import org.preznicek.vehiclesregistration.database.domain.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InsuranceService {

	@Autowired
	private InsuranceDao insuranceDao;
	
	@Transactional
	public void upsert(Insurance insurance) {
		insuranceDao.upsert(insurance);
	}
	
	@Transactional(readOnly=true)
	public Insurance getInsuranceById(Long id) {
		return insuranceDao.getInsuranceById(id);
	}
	
	@Transactional
	public void delete(Insurance insurance) {
		insuranceDao.delete(insurance);
	}
}
