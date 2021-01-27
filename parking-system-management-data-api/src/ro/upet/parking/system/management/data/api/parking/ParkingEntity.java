package ro.upet.parking.system.management.data.api.parking;

import javax.persistence.Entity;

import ro.upet.parking.system.management.data.api.base.BaseEntity;


@Entity(name = "parkings")
public class ParkingEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	/**
	 *  the name of the parking
	 */
	String name;
	
	/**
	 *  the location of the parking
	 */
	String location;
	
	/**
	 *  open time
	 */
	String opensAt;
	
	/**
	 *  closing time
	 */
	String closesAt;
	
	/**
	 * price per 1 hour
	 */
	Double pricePerHour;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOpensAt() {
		return opensAt;
	}

	public void setOpensAt(String opensAt) {
		this.opensAt = opensAt;
	}

	public String getClosesAt() {
		return closesAt;
	}

	public void setClosesAt(String closesAt) {
		this.closesAt = closesAt;
	}

	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}
	
	
	
	
}
