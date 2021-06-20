package ro.upet.parking.system.management.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Andrada
 * Model for representing a parking
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Parking {
	
	/**
	 * @return the name of the parking
	 */
	String name;

	/**
	 * @return the location of the parking
	 */
	String location;

	/**
	 * @return open time
	 */
	String opensAt;

	/**
	 * @return closing time
	 */
	String closesAt;

	
}