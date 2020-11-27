package ro.utcn.parking.system.management.model.user;

import java.time.Instant;
import java.time.LocalDate;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Andrada
 * Model for the user of the application
 */
@Value.Immutable
@JsonSerialize(as = ImtUser.class)
@JsonDeserialize(builder = ImtUser.Builder.class)
public interface User {

	/**
	 * @return identifier for the entity
	 */
	Long getId();
	
	/**
	 * @return unique code for the entity
	 */
	String getCode();	

	/**
	 * @return creation time
	 */
	Instant getCreatedAt();

	/**
	 * @return last update time
	 */
	Instant getUpdatedAt();
	
	/**
	 * @return first name and last name of the user
	 */
	String getName();
	
	/**
	 * @return birthday of the user
	 */
	LocalDate getBirthday();
	
	/**
	 * @return unique username
	 */	
	String getUsername();
	
	/**
	 * @return password
	 */
	String getPassword();
	
	/**
	 * @return the email of the user (must be unique)
	 */
	String getEmail();

	/**
	 * @return phone number for the user
	 */
	String getPhoneNumber();

	/**
	 * @return the address of the user
	 */
	String getAddress();
	
	/**
	 * @return the id of the payment options
	 */
	Long getPaymentOptionsId();
	
	/**
	 * @return the code of the payment options
	 */
	String getPaymentOptionsCode();
	
}
