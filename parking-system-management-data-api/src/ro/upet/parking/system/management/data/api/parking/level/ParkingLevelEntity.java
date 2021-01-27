package ro.upet.parking.system.management.data.api.parking.level;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.parking.ParkingEntity;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;


@Entity(name = "parking_levels")
public class ParkingLevelEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

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
