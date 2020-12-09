package ro.upet.parking.system.management.model.user;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.upet.parking.system.management.model.base.BaseModel;
import ro.upet.parking.system.management.model.base.UserType;

/**
 * @author Andrada
 * Model for the user of the application
 */
@Value.Immutable
@JsonSerialize(as = ImtUser.class)
@JsonDeserialize(builder = ImtUser.Builder.class)
public interface User extends BaseModel {
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

	
	/**
	 * @return get the type of the user
	 */
	UserType getUserType();
	
}
