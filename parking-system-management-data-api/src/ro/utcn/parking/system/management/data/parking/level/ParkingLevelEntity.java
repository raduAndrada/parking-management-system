package ro.utcn.parking.system.management.data.parking.level;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ro.utcn.parking.system.management.data.parking.ParkingEntity;

@Entity
public class ParkingLevelEntity {
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
	 *  number of the level
	 */
	String number;
	
	/**
	 *  the parking
	 */
	@ManyToOne(cascade= CascadeType.ALL)
	ParkingEntity parking;
	
}
