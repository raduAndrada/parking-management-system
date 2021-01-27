package ro.upet.parking.system.management.data.api.user;

import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;

import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.model.base.UserType;

@Entity(name = "users")
public class UserEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	private String username;

	/**
	 * password
	 */
	@Convert(converter = PasswordEncryptionConverter.class)
	private String password;

	/**
	 * the email of the user (must be unique)
	 */
	private String email;

	/**
	 * phone number for the user
	 */
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getStripeId() {
		return stripeId;
	}

	public void setStripeId(String stripeId) {
		this.stripeId = stripeId;
	}

}
