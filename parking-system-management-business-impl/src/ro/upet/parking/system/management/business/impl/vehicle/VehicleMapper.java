package ro.upet.parking.system.management.business.impl.vehicle;

import java.util.List;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.business.impl.base.GenericMapper;
import ro.upet.parking.system.management.business.impl.user.UserMapper;
import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;
import ro.upet.parking.system.management.model.vehicle.ImtVehicle;
import ro.upet.parking.system.management.model.vehicle.MdfVehicle;
import ro.upet.parking.system.management.model.vehicle.Vehicle;

/**
 * 
 * @author Andrada Mapper for the vehicle entity and model
 */
public class VehicleMapper {

	private static final GenericMapper<VehicleEntity, Vehicle> MAPPER = new GenericMapper();

	/**
	 * @param model for the vehicle
	 * @return the corresponding entity
	 */
	public static VehicleEntity toVehicleEntity(final Vehicle model) {
		final VehicleEntity entity = new VehicleEntity();
		MAPPER.mapToEntity(model, entity);
		entity.setUser(UserMapper.toUserEntity(model.getUser()));
		return entity;
	}

	/**
	 * @param entity database level vehicle
	 * @return the model for the entity
	 */
	public static Vehicle toVehicle(final VehicleEntity entity) {
		MdfVehicle model = MdfVehicle.create();
		MAPPER.mapToModel(entity, model);
		model.setUser(UserMapper.toUser(entity.getUser()));
		return model.toImmutable();
	}

	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<Vehicle> toVehicleList(final List<VehicleEntity> entityList) {
		return entityList.stream().map(VehicleMapper::toVehicle).collect(Collectors.toList());
	}
}
