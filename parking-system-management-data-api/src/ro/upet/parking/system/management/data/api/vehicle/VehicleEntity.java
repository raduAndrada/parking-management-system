package ro.upet.parking.system.management.data.api.vehicle;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.model.base.Size;

@Entity(name = "vehicles")
public class VehicleEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
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


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	
}
