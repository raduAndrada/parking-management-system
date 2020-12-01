package ro.upet.parking.system.management.model.reservation;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Andrada
 * Model for a reservation
 */
@Value.Immutable
@JsonSerialize(as = ImtReservationCreate.class)
@JsonDeserialize(builder = ImtReservationCreate.Builder.class)
public interface ReservationCreate {
	
	Reservation getReservation();
	
	

}
