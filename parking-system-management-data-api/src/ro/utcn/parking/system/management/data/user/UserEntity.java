package ro.utcn.parking.system.management.data.user;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ro.utcn.parking.system.management.data.payment.options.PaymentOptionsEntity;

@Entity
public class UserEntity {

	/**
	 * identifier for the entity
	 */
	@Id
	@GeneratedValue
	Long id;
	
	/**
	 * unique code for the entity
	 */	
	@Id
	String code;	

	/**
	 *  creation time
	 */
	Instant createdAt;

	/**
	 *  last update time
	 */
	Instant updatedAt;
	
	/**
	 *  first name and last name of the user
	 */
	String name;
	
	/**
	 *  birthday of the user
	 */
	LocalDate birthday;
	
	/**
	 *  unique username
	 */	
	String username;
	
	/**
	 *  password
	 */
	String password;
	
	/**
	 *  the email of the user (must be unique)
	 */
	String email;

	/**
	 *  phone number for the user
	 */
	String phoneNumber;

	/**
	 *  the address of the user
	 */
	String address;
	
	/**
	 * payment options of the user
	 */
	@OneToOne(mappedBy="user", cascade = CascadeType.ALL)
	PaymentOptionsEntity paymentOptions;
	
	
}
