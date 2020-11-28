package ro.utcn.parking.system.management.data.reservation;

import java.time.Instant;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ro.utcn.parking.system.management.data.parking.spot.ParkingSpotEntity;
import ro.utcn.parking.system.management.data.vehicle.VehicleEntity;
import ro.utcn.parking.system.management.model.base.ReservationStatus;

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
	VehicleEntity vehicleId;

	
	/**
	 *  parking spot
	 */
	@OneToOne
	ParkingSpotEntity parkingSpot;
}
