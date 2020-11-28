package ro.utcn.parking.system.management.data.api.user;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ro.utcn.parking.system.management.data.api.payment.options.PaymentOptionsEntity;

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

	public PaymentOptionsEntity getPaymentOptions() {
		return paymentOptions;
	}

	public void setPaymentOptions(PaymentOptionsEntity paymentOptions) {
		this.paymentOptions = paymentOptions;
	}
	
	
}
