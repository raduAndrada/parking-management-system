package ro.upet.parking.system.management.data.api.reservation;

import java.time.Instant;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;
import ro.upet.parking.system.management.model.base.ReservationStatus;

@Entity
public class ReservationEntity {
	/**
	 *  identifier for the entity
	 */
	@Id
	@GeneratedValue
	Long id;
	
	/**
	 *  unique code for the entity
	 */
	@Id
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
	 *  start time 
	 */
	Instant startTime;
	

	/**
	 *  end time 
	 */
	Instant endTime;

	/**
	 *  additional info for the reservation
	 */
	@Nullable
	String notes;

	/**
	 *  status for the reservation 
	 */
	ReservationStatus reservationStatus;
	

	/**
	 *  registration number of the vehicle that will be parked 
	 */
	String vehicleLicencePlate;
	
	/**
	 * the vehicle
	 */
	@OneToOne
	VehicleEntity vehicle;

	
	/**
	 *  parking spot
	 */
	@OneToOne
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


	public Instant getStartTime() {
		return startTime;
	}


	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}


	public Instant getEndTime() {
		return endTime;
	}


	public void setEndTime(Instant endTime) {
		this.endTime = endTime;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}


	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}


	public String getVehicleLicencePlate() {
		return vehicleLicencePlate;
	}


	public void setVehicleLicencePlate(String vehicleLicencePlate) {
		this.vehicleLicencePlate = vehicleLicencePlate;
	}


	public VehicleEntity getVehicle() {
		return vehicle;
	}


	public void setVehicle(VehicleEntity vehicle) {
		this.vehicle = vehicle;
	}


	public ParkingSpotEntity getParkingSpot() {
		return parkingSpot;
	}


	public void setParkingSpot(ParkingSpotEntity parkingSpot) {
		this.parkingSpot = parkingSpot;
	}
	
	
}
