package ro.upet.parking.system.management.data.api.parking.zone;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	 *  the letter for the zone
	 */
	private String letter;

	/**
	 *  the spots
	 */
	@OneToMany(cascade= CascadeType.MERGE, orphanRemoval = true)
	@JoinColumn(name = "parking_spot_id")
	private List<ParkingSpotEntity> parkingSpots;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JsonIgnore
	private ParkingLevelEntity parkingLevel;


	
}
