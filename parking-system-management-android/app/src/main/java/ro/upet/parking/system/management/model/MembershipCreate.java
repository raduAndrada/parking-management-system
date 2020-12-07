package ro.upet.parking.system.management.model;

import org.immutables.value.Value;


import ro.upet.parking.system.management.model.MembershipType;

/**
 * @author Andrada
 * Model entity for a membership
 */
@Value.Immutable
public interface MembershipCreate {
	
	/**
	 * @return the details of the membership
	 */
	MembershipType getMembershipType();
	
	/**
	 * @return the parking level id
	 */
	Long getParkingLevelId();
	
	/**
	 * @return the parking id
	 */
	Long getParkingId();
	
	/**
	 * @return the user id
	 */
	Long getUserId();

}
