package ro.upet.parking.system.management.model.vehicle;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;
import ro.upet.parking.system.management.model.base.BaseModel;
import ro.upet.parking.system.management.model.base.Size;
import ro.upet.parking.system.management.model.user.User;

/**
 * 
 * @author Andrada
 * Model for the vehicle
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Validated
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
