package ro.upet.parking.system.management.data.api.parking.level;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.parking.ParkingEntity;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "parking_levels")
public class ParkingLevelEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  common fields
	 */
	private BaseEntity base;
	/**
	 *  number of the level
	 */
	String number;
	
	/**
	 *  the parking
	 */
	@ManyToOne(cascade= CascadeType.MERGE)
	ParkingEntity parking;
	
	/**
	 *  parking zones
	 */
	@OneToMany(cascade= CascadeType.MERGE)
	List<ParkingZoneEntity> parkingZones;


	
}
