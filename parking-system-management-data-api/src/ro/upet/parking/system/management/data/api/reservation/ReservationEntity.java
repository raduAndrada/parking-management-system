package ro.upet.parking.system.management.data.api.reservation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	 *  identifier for the entity
	 */
	@Id
	@GeneratedValue		
	private Long id;
	
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
	private Instant endTime;

	/**
	 *  additional info for the reservation
	 */
	@Nullable
	private String notes;

	/**
	 *  status for the reservation 
	 */
	private ReservationStatus reservationStatus;
	

	/**
	 *  registration number of the vehicle that will be parked 
	 */
	private String vehicleLicencePlate;
	
	/**
	 * Cost of staying
	 */
	private BigDecimal cost;
	
	/**
	 * the user
	 */
	@ManyToOne (fetch = FetchType.LAZY)
	private UserEntity user;

	
	/**
	 *  parking spot
	 */
	@OneToOne(fetch = FetchType.LAZY)
	private ParkingSpotEntity parkingSpot;
	
	
}
