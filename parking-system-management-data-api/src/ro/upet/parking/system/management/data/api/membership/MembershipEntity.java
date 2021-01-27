package ro.upet.parking.system.management.data.api.membership;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.model.base.MembershipType;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "memberships")
public class MembershipEntity implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private BaseEntity base;
	
	/**
	 *  the type of the membership
	 */
	private MembershipType membershipType;
	
	/**
	 * the user
	 */
	@ManyToOne
	private UserEntity user;	
	

	/**
	 *  the parking spot
	 */
	@ManyToOne
	private ParkingSpotEntity parkingSpot;

	
}
