package ro.upet.parking.system.management.data.api.parking;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.upet.parking.system.management.data.api.base.BaseEntity;


@NoArgsConstructor
@AllArgsConstructor
@Data	
@Entity(name = "parkings")
public class ParkingEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  common fields
	 */
	private BaseEntity base;
	
	/**
	 *  the name of the parking
	 */
	private String name;
	
	/**
	 *  the location of the parking
	 */
	private String location;
	
	/**
	 *  open time
	 */
	private String opensAt;
	
	/**
	 *  closing time
	 */
	private String closesAt;
	
	/**
	 * price per 1 hour
	 */
	private Double pricePerHour;


	
	
}
