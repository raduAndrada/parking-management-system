package ro.upet.parking.system.management.data.api.parking.zone;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;


@Entity(name = "parking_zones")
public class ParkingZoneEntity extends BaseEntity {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
