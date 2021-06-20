package ro.upet.parking.system.management.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 
 * @author Andrada
 * Model for the vehicle
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Vehicle extends BaseModel{


	/**
	 * @return the name of the vehicle
	 */
	String name;
	
	/**
	 * @return the registration plate of the vehicle
	 */
	String licencePlate;

	/**
	 * @return the size of the vehicle
	 */
	Size size;

	/**
	 * @return the owner of the vehicle
	 */
	User user;

}
