package ro.upet.parking.system.management.data.api.parking.spot;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;


@Entity(name = "parking_spots")
public class ParkingSpotEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 *  number of the parking spot
	 */
	String number;
	
	/**
	 * true if the spot is free, false otherwise
	 */
	@ColumnDefault("true")
	Boolean available;
	
	/**
	 * true if it can be rent through a membership, false otherwise
	 */
	@ColumnDefault("false")
	Boolean rentable;
	
	/**
	 * true if it can be rented, false otherwise
	 */
	@ColumnDefault("false")
	Boolean rented;
	
	@ManyToOne
	@JsonIgnore
	ParkingZoneEntity parkingZone;


	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}


	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Boolean getRentable() {
		return rentable;
	}

	public void setRentable(Boolean rentable) {
		this.rentable = rentable;
	}

	public Boolean getRented() {
		return rented;
	}

	public void setRented(Boolean rented) {
		this.rented = rented;
	}

	public ParkingZoneEntity getParkingZone() {
		return parkingZone;
	}

	public void setParkingZone(ParkingZoneEntity parkingZone) {
		this.parkingZone = parkingZone;
	}
	
	
	
	
}
