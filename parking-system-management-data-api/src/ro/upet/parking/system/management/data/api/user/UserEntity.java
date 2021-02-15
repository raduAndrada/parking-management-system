package ro.upet.parking.system.management.data.api.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.model.base.UserType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "users")
@Builder
public class UserEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  identifier for the entity
	 */
	@Id
	@GeneratedValue		
	private Long id;
	
	
	/**
	 *  common fields
	 */
	@Embedded
	private BaseEntity base;
	
	/**
	 * first name and last name of the user
	 */
	private String name;

	/**
	 * birthday of the user
	 */
	private LocalDate birthday;

	/**
	 * unique username
	 */
	@Column(unique = true, nullable = false)
	private String username;

	/**
	 * password
	 */
	@Convert(converter = PasswordEncryptionConverter.class)
	private String password;

	/**
	 * the email of the user (must be unique)
	 */
	@Column(unique = true, nullable = false)
	private String email;

	/**
	 * phone number for the user
	 */
	@Column(length = 10)
	private String phoneNumber;

	/**
	 * the address of the user
	 */
	private String address;

	/**
	 * the type of the user
	 */
	private UserType userType;

	/**
	 * unique identifier for stripe
	 */
	private String stripeId;


}
