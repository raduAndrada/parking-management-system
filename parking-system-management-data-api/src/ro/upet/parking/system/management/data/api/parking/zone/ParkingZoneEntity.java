package ro.upet.parking.system.management.data.api.parking.zone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "parking_zones")
@Builder
public class ParkingZoneEntity implements Serializable {
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
     * the letter for the zone
     */
    private String letter;

    /**
     * the spots
     */
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "parking_spot_id")
    private List<ParkingSpotEntity> parkingSpots;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private ParkingLevelEntity parkingLevel;


    @Override
    public String toString() {
        return "ParkingZoneEntity{" +
                "id=" + id +
                ", letter='" + letter + '\'' +
                ", parkingLevel=" + parkingLevel +
                '}';
    }
}
