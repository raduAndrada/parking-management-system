package ro.upet.parking.system.management.data.api.parking.spot;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "parking_spots")
public class ParkingSpotEntity implements Serializable{
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
	 *  number of the parking spot
	 */
	@Column(nullable = false)
	private String number;
	
	/**
	 * true if the spot is free, false otherwise
	 */
	@ColumnDefault("true")
	private boolean available;
	
	/**
	 * true if it can be rent through a membership, false otherwise
	 */
	@ColumnDefault("false")
	private boolean rentable;
	
	/**
	 * true if it can be rented, false otherwise
	 */
	@ColumnDefault("false")
	private boolean rented;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JsonIgnore
	private ParkingZoneEntity parkingZone;

	
}
