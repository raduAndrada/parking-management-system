package ro.upet.parking.system.management.data.api.membership;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.model.base.MembershipType;

@Entity
public class MembershipEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  identifier for the entity
	 */
	@Id
	@GeneratedValue		
	Long id;
	
	/**
	 *  unique code for the entity
	 */
	@GeneratedValue
	String code;	

	/**
	 *  creation time
	 */
	Instant createdAt;

	/**
	 *  last update time
	 */
	Instant updatedAt;
	
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


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Instant getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}


	public Instant getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}


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
