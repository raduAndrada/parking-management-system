package ro.upet.parking.system.management.business.impl.parking.spot;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.business.impl.base.GenericMapper;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.model.parking.spot.MdfParkingSpot;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;

/**
 * @author Andrada Mapper for the parkingSpot entity and model
 */
public class ParkingSpotMapper {

    private static final GenericMapper<ParkingSpotEntity, ParkingSpot> MAPPER = new GenericMapper();

    /**
     * @param parkingSpot model for the parkingSpots
     * @return the corresponding entity
     */
    public static ParkingSpotEntity toParkingSpotEntity(final ParkingSpot parkingSpot) {
        ParkingSpotEntity entity = new ParkingSpotEntity();
        MAPPER.mapToEntity(parkingSpot, entity);
        setNullsToDefault(parkingSpot, entity);
        return entity;
    }

    /**
     * @param entity database level parkingSpot
     * @return the model for the entity
     */
    public static ParkingSpot toParkingSpot(final ParkingSpotEntity entity) {
        MdfParkingSpot model = MdfParkingSpot.create();
        MAPPER.mapToModel(entity, model);
        model.setParkingZoneLetter(Objects.nonNull(entity.getParkingZone()) ? entity.getParkingZone().getLetter() : "")
                .setParkingLevelNumber(Objects.nonNull(entity.getParkingZone())
                        && Objects.nonNull(entity.getParkingZone().getParkingLevel())
                        ? entity.getParkingZone().getParkingLevel().getNumber()
                        : "")
                .setParkingName(Objects.nonNull(entity.getParkingZone())
                        && Objects.nonNull(entity.getParkingZone().getParkingLevel())
                        && Objects.nonNull(entity.getParkingZone().getParkingLevel().getParking())
                        ? entity.getParkingZone().getParkingLevel().getParking().getName()
                        : "");
        return model.toImmutable();
    }

    /**
     * @param entityList the list with the database entities
     * @return the models for the list
     */
    public static List<ParkingSpot> toParkingSpotList(final List<ParkingSpotEntity> entityList) {
        return entityList.stream().map(ParkingSpotMapper::toParkingSpot).collect(Collectors.toList());
    }

    private static void setNullsToDefault(final ParkingSpot parkingSpot, final ParkingSpotEntity pse) {
        if (Objects.isNull(parkingSpot.getAvailable())) {
            pse.setAvailable(Boolean.TRUE);
        }

        if (Objects.isNull(parkingSpot.getRentable())) {
            pse.setRentable(Boolean.FALSE);
        }
    }
}
