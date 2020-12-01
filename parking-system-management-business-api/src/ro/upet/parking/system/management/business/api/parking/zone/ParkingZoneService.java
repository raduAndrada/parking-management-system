package ro.upet.parking.system.management.business.api.parking.zone;

import java.util.List;

import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.model.parking.zone.ParkingZone;

public interface ParkingZoneService {
	/**
	 * @param parkingZoneId the id of the parkingZone searched
	 * @return the requested parkingZone
	 */
	public ParkingZone getParkingZoneById(final Long parkingZoneId);
	
	/**
	 * @param parkingZoneCode the code of the parkingZone searched 
	 * @return the requested parkingZone
	 */
	public ParkingZone getParkingZoneByCode(final String parkingZoneCode);
	
	/**
	 * @return the list of all the parkingZones
	 */
	public List<ParkingZone> getParkingZoneList();
	
	/**
	 * @param parkingZone the entity to be added
	 * @return the added entity
	 */
	public ParkingZone addParkingZone(final ParkingZone parkingZone);
	
	/**
	 * @param parkingZone the updated parkingZone
	 * @return the updated entity
	 */
	public ParkingZone updateParkingZone(final ParkingZone parkingZone);
	
	/**
	 * @param parkingZoneId the id of the entity that will be deleted
	 * @return the deleted entity
	 * @throws BusinessException if the parkingZone does not exist
	 */
	public ParkingZone removeParkingZoneById(final Long parkingZoneId) throws BusinessException;
	
	/**
	 * @param parkingZoneCode the code of the entity that will be deleted
	 * @return the deleted entity
	 */
	public ParkingZone removeParkingZoneByCode(final String parkingZoneCode);
	
	
}
