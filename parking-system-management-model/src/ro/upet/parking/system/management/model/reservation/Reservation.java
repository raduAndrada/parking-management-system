package ro.upet.parking.system.management.model.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;
import ro.upet.parking.system.management.model.base.BaseModel;
import ro.upet.parking.system.management.model.base.ReservationStatus;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;
import ro.upet.parking.system.management.model.user.User;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author Andrada
 * Model for a reservation
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Reservation extends BaseModel{

	/**
	 * @return start time 
	 */
	@NotNull
	Instant startTime;
	

	/**
	 * @return end time 
	 */
	@NotNull
	Instant endTime;

	/**
	 * @return additional info for the reservation
	 */
	String notes;

	/**
	 * @return status for the reservation 
	 */
	ReservationStatus reservationStatus;
	

	/**
	 * @return user for the reservation
	 */
	User user;
	
	/**
	 * @return parking spot id
	 */
	@NotNull
	ParkingSpot parkingSpot;
	
	/**
	 * @return the price of the reservation
	 */
	BigDecimal cost;
	

}
