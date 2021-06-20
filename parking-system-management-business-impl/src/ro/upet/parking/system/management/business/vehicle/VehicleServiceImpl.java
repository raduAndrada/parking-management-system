package ro.upet.parking.system.management.business.vehicle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.vehicle.VehicleService;
import ro.upet.parking.system.management.business.api.vehicle.VehicleValidator;
import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;
import ro.upet.parking.system.management.data.impl.vehicle.VehicleRepository;
import ro.upet.parking.system.management.model.vehicle.Vehicle;

import java.util.List;

/**
 * @author Andrada
 * Business level logic implementation for vehicles
 */
@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepo;

    private final VehicleValidator vehicleValidator;

    public VehicleServiceImpl(VehicleRepository vehicleRepo, VehicleValidator vehicleValidator) {
        this.vehicleRepo = vehicleRepo;
        this.vehicleValidator = vehicleValidator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vehicle getById(final Long vehicleId) {
        return VehicleMapper.toVehicle(vehicleRepo.getOne(vehicleId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vehicle getByCode(final String vehicleCode) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Vehicle> getList() {
        return VehicleMapper.toVehicleList(vehicleRepo.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vehicle add(final Vehicle vehicle) throws BusinessException {
        vehicleValidator.validate(vehicle.getLicencePlate());
        return VehicleMapper.toVehicle(vehicleRepo.save(VehicleMapper.toVehicleEntity(vehicle)));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vehicle update(final Vehicle vehicle) {
        vehicleValidator.validate(vehicle.getLicencePlate());
        return VehicleMapper.toVehicle(vehicleRepo.save(VehicleMapper.toVehicleEntity(vehicle)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vehicle removeById(final Long vehicleId) throws BusinessException {
        final VehicleEntity entity = vehicleRepo.getOne(vehicleId);
        if (entity == null) {
            throw new BusinessException("The vehicle does not exist");
        }
        vehicleRepo.deleteById(vehicleId);
        return VehicleMapper.toVehicle(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vehicle removeByCode(final String vehicleCode) {
        // TODO Auto-generated method stub
        return null;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Vehicle> findByUserUsername(String username) {
        return VehicleMapper.toVehicleList(vehicleRepo.findAllByUserUsername(username));
    }

}
