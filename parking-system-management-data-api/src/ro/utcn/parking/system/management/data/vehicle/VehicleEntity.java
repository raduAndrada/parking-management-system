package ro.utcn.parking.system.management.data.vehicle;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ro.utcn.parking.system.management.data.user.UserEntity;
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

}
