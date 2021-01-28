package ro.upet.parking.system.management.data.api.parking.level;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	 *  number of the level
	 */
	@Column(nullable = false)
	private String number;
	
	/**
	 *  the parking
	 */
	@ManyToOne(cascade= CascadeType.MERGE)
	private ParkingEntity parking;
	
	/**
	 *  parking zones
	 */
	@OneToMany(cascade= CascadeType.MERGE, orphanRemoval = true)
	// @JoinColumn(name = "parking_zone_id")
	private List<ParkingZoneEntity> parkingZones;

	
}
