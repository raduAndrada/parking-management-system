package ro.upet.parking.system.management.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Andrada
 * Model for the user of the application
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
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
