package ro.upet.parking.system.management.data.api.parking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.upet.parking.system.management.data.api.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
	@Embedded
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
	private double pricePerHour;

}
