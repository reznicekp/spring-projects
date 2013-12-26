package org.preznicek.vehiclesregistration.utils;

import org.preznicek.vehiclesregistration.database.domain.vehicle.Bus;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Car;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Motorcycle;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Truck;
import org.preznicek.vehiclesregistration.database.domain.vehicle.Vehicle;

public class Helper {

	public static String getBrandOfVehicle(Vehicle vehicle) {
		String brand = null;
		
		if (vehicle instanceof Car) {
			brand = ((Car) vehicle).getBrand() != null ? ((Car) vehicle).getBrand().getValue() : vehicle.getOtherBrandName();
		} else if (vehicle instanceof Motorcycle) {
			brand = ((Motorcycle) vehicle).getBrand() != null ? ((Motorcycle) vehicle).getBrand().getValue() : vehicle.getOtherBrandName();
		} else if (vehicle instanceof Truck) {
			brand = ((Truck) vehicle).getBrand() != null ? ((Truck) vehicle).getBrand().getValue() : vehicle.getOtherBrandName();
		} else if (vehicle instanceof Bus) {
			brand = ((Bus) vehicle).getBrand() != null ? ((Bus) vehicle).getBrand().getValue() : vehicle.getOtherBrandName();
		}
		
		return brand;
	}
}
