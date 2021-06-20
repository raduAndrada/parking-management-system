package ro.upet.parking.system.management.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
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
