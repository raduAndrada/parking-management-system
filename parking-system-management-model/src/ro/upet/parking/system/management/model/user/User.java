package ro.upet.parking.system.management.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;
import ro.upet.parking.system.management.model.base.BaseModel;
import ro.upet.parking.system.management.model.base.UserType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Andrada
 * Model for the user of the application
 */

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class User extends BaseModel {
	/**
	 * @return first name and last name of the user
	 */

	String name;
	
	/**
	 * @return unique username
	 */
	@NotNull
	String username;
	
	/**
	 * @return password
	 */
	@NotNull
	String password;
	
	/**
	 * @return the email of the user (must be unique)
	 */
	@NotNull
	@Email
	String email;

	/**
	 * @return phone number for the user
	 */
	String phoneNumber;

	
	/**
	 * @return  the type of the user
	 */
	UserType userType;
	
	/**
	 * @return user's birthday
	 */
	LocalDate birthday;
	
}
