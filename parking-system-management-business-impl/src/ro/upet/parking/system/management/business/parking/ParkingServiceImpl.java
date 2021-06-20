package ro.upet.parking.system.management.business.parking;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.parking.ParkingService;
import ro.upet.parking.system.management.data.api.parking.ParkingEntity;
import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;
import ro.upet.parking.system.management.data.impl.parking.ParkingRepository;
import ro.upet.parking.system.management.data.impl.parking.level.ParkingLevelRepository;
import ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.upet.parking.system.management.data.impl.parking.zone.ParkingZoneRepository;
import ro.upet.parking.system.management.model.parking.Parking;
import ro.upet.parking.system.management.model.parking.ParkingCreate;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Andrada
 * Business level logic implementation for parkings
 */
@Service
@AllArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    private final ParkingRepository parkingRepo;

    private final ParkingLevelRepository parkingLevelRepo;

    private final ParkingZoneRepository parkingZoneRepo;

    private final ParkingSpotRepository parkingSpotRepo;

    /**
     * {@inheritDoc}
     */
    @Override
    public Parking getById(final Long parkingId) {
        return ParkingMapper.toParking(parkingRepo.getOne(parkingId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Parking getByCode(final String parkingCode) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Parking> getList() {
        return ParkingMapper.toParkingList(parkingRepo.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Parking add(final Parking parking) {
        return ParkingMapper.toParking(parkingRepo.save(ParkingMapper.toParkingEntity(parking)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Parking update(final Parking parking) {
        final ParkingEntity entity = ParkingMapper.toParkingEntity(parking);
        final ParkingEntity savedEntity = parkingRepo.save(entity);
        return ParkingMapper.toParking(savedEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Parking removeById(final Long parkingId) throws BusinessException {
        final ParkingEntity entity = parkingRepo.getOne(parkingId);
        if (entity == null) {
            throw new BusinessException("Parking does not exist");
        }
        parkingRepo.deleteById(parkingId);
        return ParkingMapper.toParking(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Parking removeByCode(final String parkingCode) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ParkingCreate configureParking(final ParkingCreate parkingCreate) {
        final ParkingEntity parking = ParkingMapper.toParkingEntity(add(parkingCreate.getParking()));
        for (int level = 0; level < parkingCreate.getNumberOfLevels(); level++) {
            ParkingLevelEntity ple = ParkingLevelEntity.builder()
                    .number("" + level)
                    .parking(parking)
                    .build();

            final List<ParkingZoneEntity> parkingZones = Lists.newArrayList();
            for (char zone = Character.toUpperCase(parkingCreate.getParkingZoneStartingLetter()); zone <= Character
                    .toUpperCase(parkingCreate.getParkingZoneEndingLetter()); zone++) {
                ParkingZoneEntity pze = ParkingZoneEntity.builder()
                        .letter("" + zone)
                        .build();
                final List<ParkingSpotEntity> parkingSpots = Lists.newArrayList();
                for (int spot = 0; spot < parkingCreate.getParkingZoneSpotNumber(); spot++) {
                    parkingSpots.add(parkingSpotRepo.save(ParkingSpotEntity.builder()
                            .number("" + zone + spot)
                            .available(Boolean.TRUE)
                            .rentable(Boolean.FALSE)
                            .rented(Boolean.FALSE)
                            .build()));
                }
                pze.setParkingSpots(parkingSpots);
                parkingZones.add(parkingZoneRepo.save(pze));
            }
            ple.setParkingZones(parkingZones);
            final ParkingLevelEntity pl = parkingLevelRepo.save(ple);
            pl.getParkingZones().forEach(pz -> {
                pz.setParkingLevel(pl);
                ParkingZoneEntity pze = parkingZoneRepo.save(pz);
                pze.getParkingSpots().forEach(ps -> {
                    ps.setParkingZone(pze);
                    parkingSpotRepo.save(ps);
                });
            });
        }

        return parkingCreate;
    }

}
