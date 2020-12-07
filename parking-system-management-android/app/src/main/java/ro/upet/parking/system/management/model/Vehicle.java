package ro.upet.parking.system.management.model;


import java.time.Instant;

import org.immutables.value.Value;


import ro.upet.parking.system.management.model.Size;
import ro.upet.parking.system.management.model.User;

/**
 * 
 * @author Andrada
 * Model for the vehicle
 */
@Value.Immutable
public interface Vehicle extends BaseModel{


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
	 * @return the owner of the vehicle
	 */
	User getUser();

}
