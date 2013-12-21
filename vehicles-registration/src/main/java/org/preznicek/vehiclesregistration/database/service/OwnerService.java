package org.preznicek.vehiclesregistration.database.service;

import org.preznicek.vehiclesregistration.database.dao.OwnerDao;
import org.preznicek.vehiclesregistration.database.domain.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OwnerService {

	@Autowired
	private OwnerDao ownerDao;
	
	@Transactional(readOnly=true)
	public Owner getOwnerByBCN(String birthCertificateNumber) {
		return ownerDao.getOwnerByBCN(birthCertificateNumber);
	}
}
