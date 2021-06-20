package ro.upet.parking.system.management.business.vehicle;

import org.modelmapper.ModelMapper;
import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;
import ro.upet.parking.system.management.model.vehicle.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author Andrada Mapper for the vehicle entity and model
 */
public class VehicleMapper {

	private static final ModelMapper MAPPER = new ModelMapper();

	/**
	 * @param model for the vehicle
	 * @return the corresponding entity
	 */
	public static VehicleEntity toVehicleEntity(final Vehicle model) {
		return MAPPER.map(model, VehicleEntity.class);
	}

	/**
	 * @param entity database level vehicle
	 * @return the model for the entity
	 */
	public static Vehicle toVehicle(final VehicleEntity entity) {
		return MAPPER.map(entity, Vehicle.class);
	}

	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<Vehicle> toVehicleList(final List<VehicleEntity> entityList) {
		return entityList.stream().map(VehicleMapper::toVehicle).collect(Collectors.toList());
	}
}
