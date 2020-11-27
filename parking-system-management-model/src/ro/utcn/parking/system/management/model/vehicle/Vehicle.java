package ro.utcn.parking.system.management.model.vehicle;


import java.time.Instant;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.utcn.parking.system.management.model.base.Size;

/**
 * 
 * @author Andrada
 * Model for the vehicle
 */
@Value.Immutable
@JsonSerialize(as = ImtVehicle.class)
@JsonDeserialize(builder = ImtVehicle.Builder.class)
public interface Vehicle {

	/**
	 * @return identifier for the entity
	 */
	Long getId();
	
	/**
	 * @return unique code for the entity
	 */
	String getCode();	

	/**
	 * @return creation time
	 */
	Instant getCreatedAt();

	/**
	 * @return last update time
	 */
	Instant getUpdatedAt();
	
	/**
	 * @return the name of the vehicle
	 */
	String getName();
	
	/**
	 * @return the registration plate of the vehicle
	 */
	String getLicencePlate();

	/**
	 * @return the size of the vehicle
	 */
	Size getSize();

	/**
	 * @return the id of the owner of the vehicle
	 */
	Long getUserId();

	/**
	 * @return the code of the owner of the vehicle
	 */
	String getUserCode();
}
