package ro.upet.parking.system.management.model;

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

}
