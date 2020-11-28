package ro.upet.parking.system.management.business.api.parking.spot;

import java.util.List;

import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;

public interface ParkingSpotService{
	/**
	 * @param parkingSpotId the id of the parkingSpot searched
	 * @return the requested parkingSpot
	 */
	public ParkingSpot getParkingSpotById(final Long parkingSpotId);
	
	/**
	 * @param parkingSpotCode the code of the parkingSpot searched 
	 * @return the requested parkingSpot
	 */
	public ParkingSpot getParkingSpotByCode(final String parkingSpotCode);
	
	/**
	 * @return the list of all the parkingSpots
	 */
	public List<ParkingSpot> getParkingSpotList();
	
	/**
	 * @param parkingSpot the entity to be added
	 * @return the added entity
	 */
	public ParkingSpot addParkingSpot(final ParkingSpot parkingSpot);
	
	/**
	 * @param parkingSpot the updated parkingSpot
	 * @return the updated entity
	 */
	public ParkingSpot updateParkingSpot(final ParkingSpot parkingSpot);
	
	/**
	 * @param parkingSpotId the id of the entity that will be deleted
	 * @return the deleted entity
	 * @throws Exception 
	 */
	public ParkingSpot removeParkingSpotById(final Long parkingSpotId) throws Exception;
	
	/**
	 * @param parkingSpotCode the code of the entity that will be deleted
	 * @return the deleted entity
	 */
	public ParkingSpot removeParkingSpotByCode(final String parkingSpotCode);
}
