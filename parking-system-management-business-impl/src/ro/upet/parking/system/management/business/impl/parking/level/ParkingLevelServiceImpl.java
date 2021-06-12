package ro.upet.parking.system.management.business.impl.parking.level;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.parking.level.ParkingLevelService;
import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;
import ro.upet.parking.system.management.data.impl.parking.ParkingRepository;
import ro.upet.parking.system.management.data.impl.parking.level.ParkingLevelRepository;
import ro.upet.parking.system.management.model.parking.level.ParkingLevel;

/**
 * @author Andrada
 * Business level logic implementation for parkingLevels
 */
@Service
public class ParkingLevelServiceImpl implements ParkingLevelService {

    final
    ParkingLevelRepository parkingLevelRepo;

    final
    ParkingRepository parkingRepo;

    public ParkingLevelServiceImpl(ParkingLevelRepository parkingLevelRepo, ParkingRepository parkingRepo) {
        this.parkingLevelRepo = parkingLevelRepo;
        this.parkingRepo = parkingRepo;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ParkingLevel getById(final Long parkingLevelId) {
        return ParkingLevelMapper.toParkingLevel(parkingLevelRepo.getOne(parkingLevelId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParkingLevel getByCode(final String parkingLevelCode) {
        // TODO Auto-generated method stub
        return null;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<ParkingLevel> getList() {
        return ParkingLevelMapper.toParkingLevelList(parkingLevelRepo.findAll());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ParkingLevel add(final ParkingLevel parkingLevel) {
        final ParkingLevelEntity entity = ParkingLevelMapper.toParkingLevelEntity(parkingLevel);
        final ParkingLevelEntity savedEntity = parkingLevelRepo.save(entity);
        return ParkingLevelMapper.toParkingLevel(savedEntity);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ParkingLevel update(final ParkingLevel parkingLevel) {
        final ParkingLevelEntity entity = ParkingLevelMapper.toParkingLevelEntity(parkingLevel);
        final ParkingLevelEntity savedEntity = parkingLevelRepo.save(entity);
        return ParkingLevelMapper.toParkingLevel(savedEntity);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ParkingLevel removeById(final Long parkingLevelId) throws BusinessException {
        final ParkingLevelEntity entity = parkingLevelRepo.getOne(parkingLevelId);
        if (entity == null) {
            throw new BusinessException("Parking Level does not exist");
        }
        parkingLevelRepo.deleteById(parkingLevelId);
        return ParkingLevelMapper.toParkingLevel(entity);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ParkingLevel removeByCode(final String parkingLevelCode) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ParkingLevel> getParkingLevelListByParking(final Long parkingId) {
        return ParkingLevelMapper.toParkingLevelList(parkingLevelRepo.findAll().stream()
                .filter(pl -> pl.getParking().getId().equals(parkingId))
                .collect(Collectors.toList()));
    }


}
