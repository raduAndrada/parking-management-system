package ro.upet.parking.system.management.data.api.parking;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	 *  identifier for the entity
	 */
	@Id
	@GeneratedValue		
	private Long id;
	
	/**
	 *  common fields
	 */
	private BaseEntity base;
	

	
	/**
	 *  the name of the parking
	 */
	@Column(unique = true)
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
	private BigDecimal pricePerHour;


	
	
}
