package ro.upet.parking.system.management.model.parking;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ro.upet.parking.system.management.model.base.BaseModel;

import java.math.BigDecimal;


/**
 * @author Andrada
 * Model for representing a parking
 */
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Parking extends BaseModel {

	
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

	
	/**
	 * @return price per hour
	 */
	BigDecimal pricePerHour;

	@Override
	public String toString() {
		return "Parking{" +
				"name='" + name + '\'' +
				", location='" + location + '\'' +
				", opensAt='" + opensAt + '\'' +
				", closesAt='" + closesAt + '\'' +
				", pricePerHour=" + pricePerHour +
				'}';
	}
}