package ro.upet.parking.system.management.data.api.membership;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.Nullable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.model.base.MembershipType;



@Entity(name = "memberships")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MembershipEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 *  identifier for the entity
	 */
	@Id
	@GeneratedValue		
	public Long id;

	/**
	 * common columns for all entities 
	 */
	@Embedded
	public BaseEntity base;
	
	/**
	 *  the type of the membership
	 */
	private MembershipType membershipType;
	
	/**
	 * the user
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity user;	
	

	/**
	 *  the parking spot
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private ParkingSpotEntity parkingSpot;
	
	
	/**
	 *  some memberships might start at a given hour
	 */
	@Nullable
	private Integer startHour;
	
	
	/**
	 *  end hour of a membership
	 */
	@Nullable
	private Integer endHour;

	/**
	 *  the cost of a membership
	 */
	private BigDecimal cost;

}
