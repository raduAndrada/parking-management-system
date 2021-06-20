package ro.upet.parking.system.management.data.api.parking.level;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.parking.ParkingEntity;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity(name = "parking_levels")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
	@Embedded
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
	@JoinColumn(name = "parking_zone_id")
	private List<ParkingZoneEntity> parkingZones;

	@Override
	public String toString() {
		return "ParkingLevelEntity{" +
				"id=" + id +
				", number='" + number + '\'' +
				'}';
	}
}
