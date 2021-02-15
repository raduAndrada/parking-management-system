package ro.upet.parking.system.management.data.api.vehicle;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.model.base.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "vehicles")
@Builder
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
