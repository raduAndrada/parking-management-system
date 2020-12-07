package ro.upet.parking.system.management.model.membership;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.upet.parking.system.management.model.base.BaseModel;
import ro.upet.parking.system.management.model.base.MembershipType;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;
import ro.upet.parking.system.management.model.user.User;


/**
 * @author Andrada
 * Model entity for a membership
 */
@Value.Immutable
@JsonSerialize(as = ImtMembership.class)
@JsonDeserialize(builder = ImtMembership.Builder.class)
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
