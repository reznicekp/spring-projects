package org.preznicek.vehiclesregistration.database.service;

import java.util.List;

import org.preznicek.vehiclesregistration.database.dao.InsuranceDao;
import org.preznicek.vehiclesregistration.database.domain.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InsuranceService {

	@Autowired
	private InsuranceDao insuranceDao;
	
	@Transactional(readOnly=true)
	public List<Insurance> getInsuranceListByCar(Long idCar) {
		List<Insurance> insuranceList = insuranceDao.getInsuranceListByCar(idCar);
		return insuranceList;
	}
	
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
