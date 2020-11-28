package ro.upet.parking.system.management.data.api.parking;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ParkingEntity {
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
	 *  the name of the parking
	 */
	String name;
	
	/**
	 *  the location of the parking
	 */
	String location;
	
	/**
	 *  open time
	 */
	Instant opensAt;
	
	/**
	 *  closing time
	 */
	Instant closesAt;

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Instant getOpensAt() {
		return opensAt;
	}

	public void setOpensAt(Instant opensAt) {
		this.opensAt = opensAt;
	}

	public Instant getClosesAt() {
		return closesAt;
	}

	public void setClosesAt(Instant closesAt) {
		this.closesAt = closesAt;
	}
	
	
}
