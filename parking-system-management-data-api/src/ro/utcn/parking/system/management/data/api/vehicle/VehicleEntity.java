package ro.utcn.parking.system.management.data.api.vehicle;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ro.utcn.parking.system.management.data.api.user.UserEntity;
import ro.utcn.parking.system.management.model.base.Size;

@Entity
public class VehicleEntity {
	/**
	 *  identifier for the entity
	 */
	@Id
	@GeneratedValue
	Long id;
	
	/**
	 *  unique code for the entity
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
	 *  the name of the vehicle
	 */
	String name;
	
	/**
	 *  the registration plate of the vehicle
	 */
	String licencePlate;

	/**
	 *  the size of the vehicle
	 */
	Size size;

	/**
	 *  the id of the owner of the vehicle
	 */
	@OneToMany
	UserEntity user;

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

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	
}
