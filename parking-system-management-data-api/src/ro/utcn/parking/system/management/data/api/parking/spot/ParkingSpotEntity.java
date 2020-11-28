package ro.utcn.parking.system.management.data.api.parking.spot;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ro.utcn.parking.system.management.data.api.parking.zone.ParkingZoneEntity;

@Entity
public class ParkingSpotEntity {
	/**
	 *  identifier for the entity	
	 */
	@Id
	@GeneratedValue
	Long id;
	
	/**
	 *  unique code for the entity
	 */
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
	 *  number of the parking spot
	 */
	String number;

	/**
	 *  the parking zone
	 */
	@ManyToOne(cascade= CascadeType.ALL)
	ParkingZoneEntity parkingZone;

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public ParkingZoneEntity getParkingZone() {
		return parkingZone;
	}

	public void setParkingZone(ParkingZoneEntity parkingZone) {
		this.parkingZone = parkingZone;
	}
	

	
}
