package ro.upet.parking.system.management.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.immutables.value.Value;

/**
 * @author Andrada
 * Model for the user of the application
 */
@Value.Immutable
@Value.Modifiable
public interface UserCreate {

	/**
	 * @return the details about the vehicle to be created
	 */
	Vehicle getVehicle();
	
	/**
	 * @return the credit card number
	 */
	String getCreditCardNumber();
	
	/**
	 * @return credit card expiration month
	 */
	String getCreditCardExpMonth();

	/**
	 * @return credit card expiration year
	 */
	String getCreditCardExpYear();
	

	/**
	 * @return credit card ccv
	 */
	String getCreditCardCCV();
}
