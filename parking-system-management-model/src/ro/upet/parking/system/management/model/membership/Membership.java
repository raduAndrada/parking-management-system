package ro.upet.parking.system.management.model.membership;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;
import ro.upet.parking.system.management.model.base.BaseModel;
import ro.upet.parking.system.management.model.base.MembershipType;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;
import ro.upet.parking.system.management.model.user.User;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author Andrada
 * Model entity for a membership
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Validated
@EqualsAndHashCode(callSuper = true)
public class Membership extends BaseModel implements Serializable  {
	
	/**
	 * @return the type of the membership
	 */
	MembershipType membershipType;
	
	/**
	 * @return the user
	 */
	@NotNull
	User user;
	
	/**
	 * @return the parking spot
	 */
	ParkingSpot parkingSpot;
	

	/**
	 *  some memberships might start at a given hour
	 */
	Integer startHour;
	
	
	/**
	 *  end hour of a membership
	 */
	Integer endHour;

	/**
	 *  the cost of a membership
	 */
	BigDecimal cost;
	
}
