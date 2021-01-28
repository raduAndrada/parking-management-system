package ro.upet.parking.system.management.business.impl.parking;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.data.api.parking.ParkingEntity;
import ro.upet.parking.system.management.model.parking.ImtParking;
import ro.upet.parking.system.management.model.parking.Parking;

/**
 * 
 * @author Andrada
 * Mapper for the parking entity and model
 */
public class ParkingMapper {

	protected static final Logger LOGGER  = Logger.getLogger(ParkingMapper.class.getName());
	
	/**
	 * @param parking model for the parking
	 * @return the corresponding entity
	 */
	public static ParkingEntity toParkingEntity(final Parking parking) {
		final ParkingEntity entity = new ParkingEntity();
		final String [] openingHours = validateOpenHours(parking.getOpensAt(), parking.getClosesAt());
		entity.getBase().setCode(parking.getCode());
		entity.setId(parking.getId());
		entity.setLocation(parking.getLocation());
		entity.setOpensAt(openingHours[0]);
		entity.setClosesAt(openingHours[1]);
		entity.setName(parking.getName());
		entity.setPricePerHour(parking.getPricePerHour());
		return entity;
	}
	
	/**
	 * @param entity database level parking
	 * @return the model for the entity
	 */
	public static Parking toParking(final ParkingEntity entity) {
		return ImtParking.builder()
				.code(entity.getBase().getCode())
				.id(entity.getId())
				.name(entity.getName())
				.closesAt(entity.getClosesAt())
				.opensAt(entity.getOpensAt())
				.location(entity.getLocation())
				.name(entity.getName())
				.pricePerHour(entity.getPricePerHour())
				.build();
	}
	
	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<Parking> toParkingList (final List<ParkingEntity> entityList) {
		return entityList.stream().map(ParkingMapper::toParking).collect(Collectors.toList());
	}
	

	
	private static String[] validateOpenHours(final String startTime, final String endTime) {
		final int [] startTimes = getHourAndMinutesFromString(startTime);
		final int [] endTimes = getHourAndMinutesFromString(endTime);
		if (startTimes[0] > endTimes[0] || (startTimes[0] == endTimes[0] && startTimes[1] > endTimes[1])) {
			return new String[] {correctHour(endTimes, "07:00"), correctHour(startTimes, "20:00")};
		} else {
			return new String[] {correctHour(startTimes, "07:00"), correctHour(endTimes, "20:00")};
		}
	
	}
	
	private  static  int[] getHourAndMinutesFromString (final String hour) {
		final String [] hourMinutes = hour.split(":");
	
		Integer hours = Integer.parseInt(hourMinutes[0].replaceAll("[^\\d.]", ""));
		Integer minutes = Integer.parseInt(hourMinutes[1].replaceAll("[^\\d.]", ""));
		return new int[] {hours, minutes};
		
	}
	
	private  static  String correctHour(final int [] times, final String correction) {
		if (times[0] >= 24 || times[0] < 0) {
			LOGGER.info(String.format("Invalid hour introduced by the user: { %s }, defaulting to: %s", times[0], correction));
			return "00:00";		
		}
		if (times[1] > 59 || times[1] < 0) {
			LOGGER.info(String.format("Invalid minutes introduced by the user: { %s }, defaulting to hh:00", times[1]));
			return times[0] + ":00";
		}
		return times[0] + ":" + times[1];
	}
}
