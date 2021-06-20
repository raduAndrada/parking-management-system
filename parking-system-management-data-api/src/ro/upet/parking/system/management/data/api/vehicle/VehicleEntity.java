package ro.upet.parking.system.management.data.api.vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.model.base.Size;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "vehicles")
public class VehicleEntity implements Serializable{
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
	 *  the name of the vehicle
	 */
	private String name;
	
	/**
	 *  the registration plate of the vehicle
	 */
	@Column(unique = true)
	private String licencePlate;

	/**
	 *  the size of the vehicle
	 */
	private Size size;

	/**
	 *  the id of the owner of the vehicle
	 */
	@ManyToOne(cascade= CascadeType.PERSIST, fetch = FetchType.LAZY)
	private UserEntity user;

}
