package ro.upet.parking.system.management.model.membership;

import java.math.BigDecimal;

import javax.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.upet.parking.system.management.model.base.MembershipType;

/**
 * @author Andrada
 * Model entity for a membership
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembershipCreate {
	
	/**
	 * @return the details of the membership
	 */
	MembershipType membershipType;
	
	/**
	 * @return the parking level id
	 */
	Long parkingLevelId;
	
	/**
	 * @return the parking id
	 */
	Long parkingId;
	
	/**
	 * @return the user id
	 */
	Long userId;
	
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
