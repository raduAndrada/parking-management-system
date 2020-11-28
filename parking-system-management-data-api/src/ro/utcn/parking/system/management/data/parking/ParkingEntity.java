package ro.utcn.parking.system.management.data.parking;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ParkingEntity {
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
	 *  the name of the parking
	 */
	String name;
	
	/**
	 *  the location of the parking
	 */
	String location;
	
	/**
	 *  open time
	 */
	Instant opensAt;
	
	/**
	 *  closing time
	 */
	Instant closesAt;
}
