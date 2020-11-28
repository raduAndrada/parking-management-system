package ro.upet.parking.system.management.business.api.vehicle;

import java.util.List;

import ro.upet.parking.system.management.model.vehicle.Vehicle;

public interface VehicleService {
	/**
	 * @param vehicleId the id of the vehicle searched
	 * @return the requested vehicle
	 */
	public Vehicle getVehicleById(final Long vehicleId);
	
	/**
	 * @param vehicleCode the code of the vehicle searched 
	 * @return the requested vehicle
	 */
	public Vehicle getVehicleByCode(final String vehicleCode);
	
	/**
	 * @return the list of all the vehicles
	 */
	public List<Vehicle> getVehicleList();
	
	/**
	 * @param vehicle the entity to be added
	 * @return the added entity
	 */
	public Vehicle addVehicle(final Vehicle vehicle);
	
	/**
	 * @param vehicle the updated vehicle
	 * @return the updated entity
	 */
	public Vehicle updateVehicle(final Vehicle vehicle);
	
	/**
	 * @param vehicleId the id of the entity that will be deleted
	 * @return the deleted entity
	 * @throws Exception 
	 */
	public Vehicle removeVehicleById(final Long vehicleId) throws Exception;
	
	/**
	 * @param vehicleCode the code of the entity that will be deleted
	 * @return the deleted entity
	 */
	public Vehicle removeVehicleByCode(final String vehicleCode);
}
