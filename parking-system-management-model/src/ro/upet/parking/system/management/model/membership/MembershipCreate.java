package ro.upet.parking.system.management.model.membership;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.upet.parking.system.management.model.base.MembershipType;

/**
 * @author Andrada
 * Model entity for a membership
 */
@Value.Immutable
@JsonSerialize(as = ImtMembershipCreate.class)
@JsonDeserialize(builder = ImtMembershipCreate.Builder.class)
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
