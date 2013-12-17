package org.preznicek.vehiclesregistration.database.service;

import java.text.ParseException;
import java.util.List;

import org.preznicek.vehiclesregistration.database.dao.VehicleDao;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Vehicle;
import org.preznicek.vehiclesregistration.model.formbean.SearchFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleService {

	@Autowired
	protected VehicleDao vehicleDao;
	
	@Transactional
	public void upsert(Vehicle vehicle) {
		vehicleDao.upsert(vehicle);
	}
	
	@Transactional(readOnly=true)
	public List<Vehicle> getVehicleList(SearchFormBean searchFormBean) throws ParseException {
		return vehicleDao.getVehicleList(searchFormBean);
	}
	
	@Transactional(readOnly=true)
	public Vehicle getVehicleById(Long id, Class<? extends Vehicle> clazz) {
		return vehicleDao.getVehicleById(id, clazz);
	}
	
	@Transactional(readOnly=true)
	public List<Vehicle> getVehicleListByOwnerId(Long ownerId) {
		return vehicleDao.getVehicleListByOwnerId(ownerId);
	}
}
