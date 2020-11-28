package ro.utcn.parking.system.management.data.parking.spot;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ro.utcn.parking.system.management.data.parking.zone.ParkingZoneEntity;

@Entity
public class ParkingSpotEntity {
	/**
	 *  identifier for the entity	
	 */
	@Id
	@GeneratedValue
	Long id;
	
	/**
	 *  unique code for the entity
	 */
	String code;	

	/**
	 *  creation time
	 */
	Instant createdAt;

	/**
	 *  last update time
	 */
	Instant updatedAt;
	
	/**
	 *  number of the parking spot
	 */
	String number;

	/**
	 *  the parking zone
	 */
	@ManyToOne(cascade= CascadeType.ALL)
	ParkingZoneEntity parkingZone;
	

	
}
