package ro.upet.parking.system.management.model.vehicle;


import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.upet.parking.system.management.model.base.BaseModel;
import ro.upet.parking.system.management.model.base.Size;
import ro.upet.parking.system.management.model.user.User;

/**
 * 
 * @author Andrada
 * Model for the vehicle
 */
@Value.Immutable
@JsonSerialize(as = ImtVehicle.class)
@JsonDeserialize(builder = ImtVehicle.Builder.class)
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
