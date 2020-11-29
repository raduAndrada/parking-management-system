package ro.upet.parking.system.management.business.api.parking;

import java.util.List;

import ro.upet.parking.system.management.model.parking.Parking;
import ro.upet.parking.system.management.model.parking.ParkingCreate;

public interface ParkingService{
	/**
	 * @param parkingId the id of the parking searched
	 * @return the requested parking
	 */
	public Parking getParkingById(final Long parkingId);
	
	/**
	 * @param parkingCode the code of the parking searched 
	 * @return the requested parking
	 */
	public Parking getParkingByCode(final String parkingCode);
	
	/**
	 * @return the list of all the parkings
	 */
	public List<Parking> getParkingList();
	
	/**
	 * @param parking the entity to be added
	 * @return the added entity
	 */
	public Parking addParking(final Parking parking);
	
	/**
	 * @param parking the updated parking
	 * @return the updated entity
	 */
	public Parking updateParking(final Parking parking);
	
	/**
	 * @param parkingId the id of the entity that will be deleted
	 * @return the deleted entity
	 * @throws Exception 
	 */
	public Parking removeParkingById(final Long parkingId) throws Exception;
	
	/**
	 * @param parkingCode the code of the entity that will be deleted
	 * @return the deleted entity
	 */
	public Parking removeParkingByCode(final String parkingCode);
	

	/**
	 * @param parkingCreate the configuration for the parking
	 * @return the createad parking
	 */
	public ParkingCreate configureParking(final ParkingCreate parkingCreate);
}
