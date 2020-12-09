package ro.upet.parking.system.management.model;

import org.immutables.value.Value;

@Value.Immutable
@Value.Modifiable
public interface UserUpdate {
    /**
     * @return the username
     */
    String getUsername();

    /**
     * @return password
     */
    String getPassword();

    /**
     * @return password confirm
     */
    String getPasswordConfirm();


    /**
     * @return the email of the user (must be unique)
     */
    String getEmail();

    /**
     * @return phone number for the user
     */
    String getPhoneNumber();

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

    /**
     * @return the registration plate of the vehicle
     */
    String getLicencePlate();

}
