package ro.upet.parking.system.management.data.api.reservation;

import java.io.Serializable;
import java.time.Instant;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.model.base.ReservationStatus;

@Entity
@Table(name = "reservations")
public class ReservationEntity implements Serializable {
	
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
	 * Cost of staying
	 */
	Double cost;
	
	/**
	 * the user
	 */
	@ManyToOne
	UserEntity user;

	
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

	public ParkingSpotEntity getParkingSpot() {
		return parkingSpot;
	}


	public void setParkingSpot(ParkingSpotEntity parkingSpot) {
		this.parkingSpot = parkingSpot;
	}


	public UserEntity getUser() {
		return user;
	}


	public void setUser(UserEntity user) {
		this.user = user;
	}


	public Double getCost() {
		return cost;
	}


	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	
	
}
