package ro.utcn.parking.system.management.data.parking.zone;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ro.utcn.parking.system.management.data.parking.level.ParkingLevelEntity;

@Entity
public class ParkingZoneEntity {
	/**
	 *  identifier for the entity
	 */
	@Id
	@GeneratedValue
	Long id;
	
	/**
	 *  unique code for the entity
	 */
	@Id
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
	 *  the letter for the zone
	 */
	String letter;

	/**
	 *  the level
	 */
	@ManyToOne(cascade= CascadeType.ALL)
	ParkingLevelEntity parkingLevel;

}
