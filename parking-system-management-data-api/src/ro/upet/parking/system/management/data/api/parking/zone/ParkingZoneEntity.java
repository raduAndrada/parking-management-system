package ro.upet.parking.system.management.data.api.parking.zone;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "parking_zones")
public class ParkingZoneEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  common fields
	 */
	private BaseEntity base;
	/**
	 *  the letter for the zone
	 */
	String letter;

	/**
	 *  the spots
	 */
	@OneToMany(cascade= CascadeType.MERGE)
	List<ParkingSpotEntity> parkingSpots;
	
	@ManyToOne
	@JsonIgnore
	ParkingLevelEntity parkingLevel;


	
}
