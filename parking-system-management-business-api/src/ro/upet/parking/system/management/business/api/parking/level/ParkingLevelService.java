package ro.upet.parking.system.management.business.api.parking.level;

import java.util.List;

import ro.upet.parking.system.management.model.parking.level.ParkingLevel;

public interface ParkingLevelService {
	/**
	 * @param parkingLevelId the id of the parkingLevel searched
	 * @return the requested parkingLevel
	 */
	public ParkingLevel getParkingLevelById(final Long parkingLevelId);
	
	/**
	 * @param parkingLevelCode the code of the parkingLevel searched 
	 * @return the requested parkingLevel
	 */
	public ParkingLevel getParkingLevelByCode(final String parkingLevelCode);
	
	/**
	 * @return the list of all the parkingLevels
	 */
	public List<ParkingLevel> getParkingLevelList();
	
	/**
	 * @param parkingLevel the entity to be added
	 * @return the added entity
	 */
	public ParkingLevel addParkingLevel(final ParkingLevel parkingLevel);
	
	/**
	 * @param parkingLevel the updated parkingLevel
	 * @return the updated entity
	 */
	public ParkingLevel updateParkingLevel(final ParkingLevel parkingLevel);
	
	/**
	 * @param parkingLevelId the id of the entity that will be deleted
	 * @return the deleted entity
	 * @throws Exception if parking level doesn't exist
	 */
	public ParkingLevel removeParkingLevelById(final Long parkingLevelId) throws Exception;
	
	/**
	 * @param parkingLevelCode the code of the entity that will be deleted
	 * @return the deleted entity
	 */
	public ParkingLevel removeParkingLevelByCode(final String parkingLevelCode);
	
	/**
	 * @param parkingId the parking
	 * @return the list of all the levels of the parking
	 */
	public List<ParkingLevel> getParkingLevelListByParking(final Long parkingId);
}
