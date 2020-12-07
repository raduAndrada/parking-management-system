package ro.upet.parking.system.management.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.immutables.value.Value;

import java.time.Instant;

import ro.upet.parking.system.management.model.MembershipType;
import ro.upet.parking.system.management.model.ParkingSpot;
import ro.upet.parking.system.management.model.User;


/**
 * @author Andrada
 * Model entity for a membership
 */
@Value.Immutable
public interface Membership extends BaseModel {

	/**
	 * @return the type of the membership
	 */
	MembershipType getMembershipType();
	
	/**
	 * @return the user
	 */
	User getUser();
	
	/**
	 * @return the parking spot
	 */
	ParkingSpot getParkingSpot();
	

	
}
