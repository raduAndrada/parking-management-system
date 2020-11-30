package ro.upet.parking.system.management.data.api.user;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import ro.upet.parking.system.management.model.base.UserType;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * identifier for the entity
	 */
	@Id
	@GeneratedValue
	Long id;
	
	/**
	 * unique code for the entity
	 */	
	@GeneratedValue
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
	 * the type of the user 
	 */
	UserType userType;
	
	/**
	 *  unique identifier for stripe
	 */
	String stripeId;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

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
