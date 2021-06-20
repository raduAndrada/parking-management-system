package ro.upet.parking.system.management.data.api.parking.spot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name = "parking_spots")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     * identifier for the entity
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * common fields
     */
    @Embedded
    private BaseEntity base;

    /**
     * number of the parking spot
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private ParkingZoneEntity parkingZone;

    @Override
    public String toString() {
        return "ParkingSpotEntity{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", available=" + available +
                ", rentable=" + rentable +
                ", rented=" + rented +
                '}';
    }
}
