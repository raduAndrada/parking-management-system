package ro.upet.parking.system.management.model.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdate {
	
	/**
	 * @return the username
	 */
	String username;

    /**
     * @return password
     */
    String password;

    /**
     * @return password confirm
     */
    String passwordConfirm;


    /**
     * @return the email of the user (must be unique)
     */
    String email;

    /**
     * @return phone number for the user
     */
    String phoneNumber;

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

    /**
     * @return the registration plate of the vehicle
     */
    String licencePlate;

}
