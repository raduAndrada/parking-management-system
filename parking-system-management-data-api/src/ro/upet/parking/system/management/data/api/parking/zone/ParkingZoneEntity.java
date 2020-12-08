package ro.upet.parking.system.management.data.api.parking.zone;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;

@Entity
@Table(name = "parking_zones")
public class ParkingZoneEntity implements Serializable {
	
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
	 *  the letter for the zone
	 */
	String letter;

	/**
	 *  the spots
	 */
	@OneToMany(cascade= CascadeType.MERGE)
	List<ParkingSpotEntity> parkingSpots;
	
	@ManyToOne
	@JsonIgnore
	ParkingLevelEntity parkingLevel;

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

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public List<ParkingSpotEntity> getParkingSpots() {
		return parkingSpots;
	}

	public void setParkingSpots(List<ParkingSpotEntity> parkingSpots) {
		this.parkingSpots = parkingSpots;
	}

	public ParkingLevelEntity getParkingLevel() {
		return parkingLevel;
	}

	public void setParkingLevel(ParkingLevelEntity parkingLevel) {
		this.parkingLevel = parkingLevel;
	}


	
}
