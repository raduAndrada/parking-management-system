package ro.upet.parking.system.management.business.parking.level;

import org.modelmapper.ModelMapper;
import ro.upet.parking.system.management.business.parking.zone.ParkingZoneMapper;
import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;
import ro.upet.parking.system.management.model.parking.level.ParkingLevel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrada Mapper for the parkingLevel entity and model
 */
public class ParkingLevelMapper {

    private static final ModelMapper MAPPER = new ModelMapper();

    /**
     * @param parkingLevel model for the parkingLevel
     * @return the corresponding entity
     */
    public static ParkingLevelEntity toParkingLevelEntity(final ParkingLevel parkingLevel) {
        return MAPPER.map(parkingLevel, ParkingLevelEntity.class);
    }

    /**
     * @param entity database level parkingLevel
     * @return the model for the entity
     */
    public static ParkingLevel toParkingLevel(final ParkingLevelEntity entity) {
        final List<ParkingZoneEntity> parkingZones = entity.getParkingZones();
        entity.setParkingZones(null);
        final ParkingLevel parkingLevel = MAPPER.map(entity, ParkingLevel.class);
        parkingLevel.setParkingZones(ParkingZoneMapper.toParkingZoneList(parkingZones));
        return parkingLevel;

    }

    /**
     * @param entityList the list with the database entities
     * @return the models for the list
     */
    public static List<ParkingLevel> toParkingLevelList(final List<ParkingLevelEntity> entityList) {
        return entityList.stream().map(ParkingLevelMapper::toParkingLevel).collect(Collectors.toList());
    }
}
