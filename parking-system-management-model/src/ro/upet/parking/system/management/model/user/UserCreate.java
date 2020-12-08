package ro.upet.parking.system.management.model.user;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.upet.parking.system.management.model.vehicle.Vehicle;

/**
 * @author Andrada
 * Model for the user of the application
 */
@Value.Immutable
@JsonSerialize(as = ImtUserCreate.class)
@JsonDeserialize(builder = ImtUserCreate.Builder.class)
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
