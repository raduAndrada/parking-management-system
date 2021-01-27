package ro.upet.parking.system.management.data.api.vehicle;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.model.base.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "vehicles")
public class VehicleEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  common fields
	 */
	private BaseEntity base;
	/**
	 *  the name of the vehicle
	 */
	String name;
	
	/**
	 *  the registration plate of the vehicle
	 */
	String licencePlate;

	/**
	 *  the size of the vehicle
	 */
	Size size;

	/**
	 *  the id of the owner of the vehicle
	 */
	@ManyToOne(cascade= CascadeType.PERSIST)
	UserEntity user;

	
}
