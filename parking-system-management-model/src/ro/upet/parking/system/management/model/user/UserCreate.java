package ro.upet.parking.system.management.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.upet.parking.system.management.model.vehicle.Vehicle;

/**
 * @author Andrada
 * Model for the user of the application
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreate {

	/**
	 * @return the details about the vehicle to be created
	 */
	Vehicle vehicle;
	
	/**
	 * @return the credit card number
	 */
	String creditCardNumber;
	
	/**
	 * @return credit card expiration month
	 */
	String creditCardExpMonth;

	/**
	 * @return credit card expiration year
	 */
	String creditCardExpYear;
	

	/**
	 * @return credit card ccv
	 */
	String creditCardCCV;
}
