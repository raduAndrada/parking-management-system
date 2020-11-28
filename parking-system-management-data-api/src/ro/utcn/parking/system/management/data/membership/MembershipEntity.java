package ro.utcn.parking.system.management.data.membership;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ro.utcn.parking.system.management.data.parking.spot.ParkingSpotEntity;
import ro.utcn.parking.system.management.data.user.UserEntity;
import ro.utcn.parking.system.management.model.base.MembershipType;

@Entity
public class MembershipEntity {
	
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
	 *  the type of the membership
	 */
	MembershipType membershipType;
	
	/**
	 * the user
	 */
	@ManyToOne
	UserEntity user;	
	

	/**
	 *  the parking spot
	 */
	@ManyToOne
	ParkingSpotEntity parkingSpot;
}
