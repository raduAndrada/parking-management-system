package ro.upet.parking.system.management.data.api.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.model.base.ReservationStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
	@Embedded
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
	private double cost;
	
	/**
	 * the user
	 */
	@ManyToOne (fetch = FetchType.LAZY)
	private UserEntity user;

	
	/**
	 *  parking spot
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private ParkingSpotEntity parkingSpot;

	@Override
	public String toString() {
		return "ReservationEntity{" +
				"id=" + id +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", reservationStatus=" + reservationStatus +
				", cost=" + cost +
				", user=" + user +
				'}';
	}
}
