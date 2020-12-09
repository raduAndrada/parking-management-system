package ro.upet.parking.system.management.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.Instant;

import org.immutables.value.Value;



import ro.upet.parking.system.management.model.UserType;

/**
 * @author Andrada
 * Model for the user of the application
 */
@Value.Immutable
@JsonSerialize(as = ImtUser.class)
@JsonDeserialize(builder = ImtUser.Builder.class)
public interface User extends BaseModel{

	/**
	 * @return first name and last name of the user
	 */
	String getName();

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


}
