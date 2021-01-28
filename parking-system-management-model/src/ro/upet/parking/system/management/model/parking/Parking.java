package ro.upet.parking.system.management.model.parking;

import java.math.BigDecimal;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.upet.parking.system.management.model.base.BaseModel;


/**
 * @author Andrada
 * Model for representing a parking
 */
@Value.Immutable
@JsonSerialize(as = ImtParking.class)
@JsonDeserialize(builder = ImtParking.Builder.class)
public interface Parking extends BaseModel {

	
	/**
	 * @return the name of the parking
	 */
	String getName();
	
	/**
	 * @return the location of the parking
	 */
	String getLocation();
	
	/**
	 * @return open time
	 */
	String getOpensAt();
	
	/**
	 * @return closing time
	 */
	String getClosesAt();

	
	/**
	 * @return price per hour
	 */
	BigDecimal getPricePerHour();
	
}