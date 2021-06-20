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
public class User extends BaseModel{

	/**
	 * @return first name and last name of the user
	 */
	String name;

	/**
	 * @return unique username
	 */
	String username;

	/**
	 * @return password
	 */
	String password;

	/**
	 * @return the email of the user (must be unique)
	 */
	String email;

	/**
	 * @return phone number for the user
	 */
	String phoneNumber;


}
