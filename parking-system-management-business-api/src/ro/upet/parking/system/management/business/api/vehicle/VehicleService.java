package ro.upet.parking.system.management.business.api.vehicle;

import java.util.List;

import ro.upet.parking.system.management.business.api.core.BusinessException;
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
	 * @throws BusinessException if the vehicle number already exists in the database or if it doesn't pass the validation
	 */
	public Vehicle addVehicle(final Vehicle vehicle) throws BusinessException;
	
	/**
	 * @param vehicle the updated vehicle
	 * @return the updated entity
	 */
	public Vehicle updateVehicle(final Vehicle vehicle);
	
	/**
	 * @param vehicleId the id of the entity that will be deleted
	 * @return the deleted entity
	 * @throws BusinessException  if the vehicle does not exist
	 */
	public Vehicle removeVehicleById(final Long vehicleId) throws BusinessException;
	
	/**
	 * @param vehicleCode the code of the entity that will be deleted
	 * @return the deleted entity
	 */
	public Vehicle removeVehicleByCode(final String vehicleCode);
}
