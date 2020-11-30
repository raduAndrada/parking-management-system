package ro.upet.parking.system.management.data.api.parking.level;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ro.upet.parking.system.management.data.api.parking.ParkingEntity;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;

@Entity
@Table(name = "parking_levels")
public class ParkingLevelEntity implements Serializable {
	
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
	 *  number of the level
	 */
	String number;
	
	/**
	 *  the parking
	 */
	@ManyToOne(cascade= CascadeType.MERGE)
	ParkingEntity parking;
	
	/**
	 *  parking zones
	 */
	@OneToMany(cascade= CascadeType.MERGE)
	List<ParkingZoneEntity> parkingZones;

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

	public ParkingEntity getParking() {
		return parking;
	}

	public void setParking(ParkingEntity parking) {
		this.parking = parking;
	}

	public List<ParkingZoneEntity> getParkingZones() {
		return parkingZones;
	}

	public void setParkingZones(List<ParkingZoneEntity> parkingZones) {
		this.parkingZones = parkingZones;
	}

	
}
