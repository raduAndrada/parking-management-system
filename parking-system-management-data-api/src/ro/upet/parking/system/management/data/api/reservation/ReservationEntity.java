package ro.upet.parking.system.management.data.api.reservation;

import java.io.Serializable;
import java.time.Instant;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.model.base.ReservationStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "reservations")
public class ReservationEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  common fields
	 */
	private BaseEntity base;
	
	/**
	 *  start time 
	 */
	private Instant startTime;
	

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
	
	

	
	
}
