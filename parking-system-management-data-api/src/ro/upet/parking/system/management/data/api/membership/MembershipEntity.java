package ro.upet.parking.system.management.data.api.membership;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.model.base.MembershipType;

@Entity(name = "memberships")
public class MembershipEntity extends BaseEntity {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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


	public MembershipType getMembershipType() {
		return membershipType;
	}


	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
	}


	public UserEntity getUser() {
		return user;
	}


	public void setUser(UserEntity user) {
		this.user = user;
	}


	public ParkingSpotEntity getParkingSpot() {
		return parkingSpot;
	}


	public void setParkingSpot(ParkingSpotEntity parkingSpot) {
		this.parkingSpot = parkingSpot;
	}
	
	
}
