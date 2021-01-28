package ro.upet.parking.system.management.business.impl.vehicle;

import java.util.List;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.business.impl.user.UserMapper;
import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;
import ro.upet.parking.system.management.model.vehicle.ImtVehicle;
import ro.upet.parking.system.management.model.vehicle.Vehicle;

/**
 * 
 * @author Andrada
 * Mapper for the vehicle entity and model
 */
public class VehicleMapper {

	/**
	 * @param vehicle model for the vehicle
	 * @return the corresponding entity
	 */
	public static VehicleEntity toVehicleEntity(final Vehicle vehicle) {
		final VehicleEntity entity = new VehicleEntity();
		entity.getBase().setCode(vehicle.getCode());
		entity.setId(vehicle.getId());
		entity.setLicencePlate(vehicle.getLicencePlate());
		entity.setName(vehicle.getName());
		entity.setSize(vehicle.getSize());
		entity.setUser(UserMapper.toUserEntity(vehicle.getUser()));
		return entity;
	}
	
	/**
	 * @param entity database level vehicle
	 * @return the model for the entity
	 */
	public static Vehicle toVehicle(final VehicleEntity entity) {
		return ImtVehicle.builder()
				.code(entity.getBase().getCode())
				.id(entity.getId())
				.user(UserMapper.toUser(entity.getUser()))
				.licencePlate(entity.getLicencePlate())
				.name(entity.getName())
				.size(entity.getSize())
				.build();
	}
	
	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<Vehicle> toVehicleList (final List<VehicleEntity> entityList) {
		return entityList.stream().map(VehicleMapper::toVehicle).collect(Collectors.toList());
	}
}
