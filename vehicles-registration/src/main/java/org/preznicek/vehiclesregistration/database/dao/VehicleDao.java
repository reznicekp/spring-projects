package org.preznicek.vehiclesregistration.database.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.preznicek.vehiclesregistration.database.PageData;
import org.preznicek.vehiclesregistration.database.domain.Owner;
import org.preznicek.vehiclesregistration.database.domain.SearchView;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Vehicle;
import org.preznicek.vehiclesregistration.model.formbean.SearchFormBean;
import org.preznicek.vehiclesregistration.utils.Constants;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class VehicleDao extends BaseDao {

	/**
	 * Ulozi vozidlo do databaze.
	 * @param vehicle	Vozidlo.
	 */
	public void upsert(Vehicle vehicle) {
		getSession();
		session.saveOrUpdate(vehicle);
	}
	
	/**
	 * Vyhleda vozidla podle zadanych kriterii.
	 * @param searchFormBean	Formular s vyhledavacimi kriterii.
	 * @param pageNumber		Cislo stranky ve strankovani.
	 * @return					Vyhledana vozidla.
	 * @throws ParseException	Pokud neni korektne zadany datum konce STK (nenastane, protoze se tato chyba odchyti uz ve validaci).
	 */
	@SuppressWarnings("unchecked")
	public PageData getVehicleList(SearchFormBean searchFormBean, int pageNumber) throws ParseException {
		getSession();
		
		Criteria criteria = session.createCriteria(SearchView.class);
		
		if (searchFormBean.getSearchVehicleFormBean() != null) {
			if (!StringUtils.isEmpty(searchFormBean.getSearchVehicleFormBean().getPlateNumber())) {
				criteria.add(Restrictions.ilike("vehiclePlateNumber", searchFormBean.getSearchVehicleFormBean().getPlateNumber(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchVehicleFormBean().getBrand())) {
				criteria.add(Restrictions.ilike("vehicleBrand", searchFormBean.getSearchVehicleFormBean().getBrand(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchVehicleFormBean().getModel())) {
				criteria.add(Restrictions.ilike("vehicleModel", searchFormBean.getSearchVehicleFormBean().getModel(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchVehicleFormBean().getMakingYearFrom())) {
				criteria.add(Restrictions.ge("vehicleMakingYear", Integer.valueOf(searchFormBean.getSearchVehicleFormBean().getMakingYearFrom())));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchVehicleFormBean().getMakingYearTo())) {
				criteria.add(Restrictions.le("vehicleMakingYear", Integer.valueOf(searchFormBean.getSearchVehicleFormBean().getMakingYearTo())));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchVehicleFormBean().getMotEndFrom())) {
				criteria.add(Restrictions.ge("vehicleMotEnd", new SimpleDateFormat(Constants.MOT_DATE_FORMAT).parse(searchFormBean.getSearchVehicleFormBean().getMotEndFrom())));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchVehicleFormBean().getMotEndTo())) {
				criteria.add(Restrictions.le("vehicleMotEnd", new SimpleDateFormat(Constants.MOT_DATE_FORMAT).parse(searchFormBean.getSearchVehicleFormBean().getMotEndTo())));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchVehicleFormBean().getInsuranceCompany())) {
				criteria.add(Restrictions.ilike("insuranceCompany", searchFormBean.getSearchVehicleFormBean().getInsuranceCompany(), MatchMode.ANYWHERE));
				if (searchFormBean.getSearchVehicleFormBean().getOnlyActiveInsurance() != null) {		// zaskrtnuto - "", odskrtnuto - null
					criteria.add(Restrictions.and(Restrictions.le("insuranceActiveFrom", new Date()), Restrictions.ge("insuranceActiveTo", new Date())));
				}
			}
		}
		if (searchFormBean.getSearchOwnerFormBean() != null) {
			if (!StringUtils.isEmpty(searchFormBean.getSearchOwnerFormBean().getFirstname())) {
				criteria.add(Restrictions.ilike("ownerFirstname", searchFormBean.getSearchOwnerFormBean().getFirstname(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchOwnerFormBean().getLastname())) {
				criteria.add(Restrictions.ilike("ownerLastname", searchFormBean.getSearchOwnerFormBean().getLastname(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(searchFormBean.getSearchOwnerFormBean().getBirthCertificateNumber())) {
				criteria.add(Restrictions.ilike("ownerBirthCertificateNumber", searchFormBean.getSearchOwnerFormBean().getBirthCertificateNumber(), MatchMode.ANYWHERE));
			}
		}
		
		// celkovy pocet odpovidajicich zaznamu v databazi
		int count = criteria.list().size();
		
		criteria.setFirstResult((pageNumber - 1) * Constants.PAGE_SIZE);
		criteria.setMaxResults(Constants.PAGE_SIZE);
		
		// zaznamy s ohledem na strankovani
		List<SearchView> searchViewList = criteria.list();
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		
		// mapovani na objekty tridy Vehicle
		for (SearchView searchView : searchViewList) {
			Vehicle vehicle = new Vehicle();
			vehicle.setId(searchView.getVehicleId());
			vehicle.setVehicleType(searchView.getVehicleType());
			vehicle.setPlateNumber(searchView.getVehiclePlateNumber());
			vehicle.setOtherBrandName(searchView.getVehicleBrand());
			vehicle.setModel(searchView.getVehicleModel());
			vehicle.setMakingYear(searchView.getVehicleMakingYear());
			vehicle.setMotEnd(searchView.getVehicleMotEnd());
			
			Owner owner = new Owner();
			owner.setFirstname(searchView.getOwnerFirstname());
			owner.setLastname(searchView.getOwnerLastname());
			vehicle.setOwner(owner);
			
			vehicleList.add(vehicle);
		}
		
		return new PageData(vehicleList, count);
	}
	
	/**
	 * Vrati detail vozidla podle jeho ID.
	 * @param id		ID vozidla.
	 * @param clazz		Trida urcujici typ vozidla.
	 * @return			Detail vozidla.
	 */
	public Vehicle getVehicleById(Long id, Class<? extends Vehicle> clazz) {
		getSession();
		return (Vehicle) session.get(clazz, id);
	}
}
