package ro.upet.parking.system.management.model.membership;

import java.math.BigDecimal;

import javax.annotation.Nullable;

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
	

	/**
	 *  some memberships might start at a given hour
	 */
	@Nullable
	Integer getStartHour();
	
	
	/**
	 *  end hour of a membership
	 */
	@Nullable
	Integer getEndHour();

	/**
	 *  the cost of a membership
	 */
	BigDecimal getCost();
	
}
