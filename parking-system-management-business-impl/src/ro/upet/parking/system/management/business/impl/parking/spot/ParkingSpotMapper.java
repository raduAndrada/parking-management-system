package ro.upet.parking.system.management.business.impl.parking.spot;

import org.modelmapper.ModelMapper;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Andrada Mapper for the parkingSpot entity and model
 */
public class ParkingSpotMapper {

    private static final ModelMapper MAPPER = new ModelMapper();

    /**
     * @param parkingSpot model for the parkingSpots
     * @return the corresponding entity
     */
    public static ParkingSpotEntity toParkingSpotEntity(final ParkingSpot parkingSpot) {
        return MAPPER.map(parkingSpot, ParkingSpotEntity.class);
    }

    /**
     * @param entity database level parkingSpot
     * @return the model for the entity
     */
    public static ParkingSpot toParkingSpot(final ParkingSpotEntity entity) {
        ParkingSpot model = MAPPER.map(entity, ParkingSpot.class);
        model.setParkingZoneLetter(Objects.nonNull(entity.getParkingZone()) ? entity.getParkingZone().getLetter() : "");
        model.setParkingLevelNumber(Objects.nonNull(entity.getParkingZone())
                && Objects.nonNull(entity.getParkingZone().getParkingLevel())
                ? entity.getParkingZone().getParkingLevel().getNumber()
                : "");
        model.setParkingName(Objects.nonNull(entity.getParkingZone())
                && Objects.nonNull(entity.getParkingZone().getParkingLevel())
                && Objects.nonNull(entity.getParkingZone().getParkingLevel().getParking())
                ? entity.getParkingZone().getParkingLevel().getParking().getName()
                : "");

        return model;
    }

    /**
     * @param entityList the list with the database entities
     * @return the models for the list
     */
    public static List<ParkingSpot> toParkingSpotList(final List<ParkingSpotEntity> entityList) {
        return entityList.stream().map(ParkingSpotMapper::toParkingSpot).collect(Collectors.toList());
    }

}
