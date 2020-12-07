package ro.upet.parking.system.management.model.base;

import java.time.Instant;

import org.immutables.value.Value;


public interface BaseModel {
	
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
}