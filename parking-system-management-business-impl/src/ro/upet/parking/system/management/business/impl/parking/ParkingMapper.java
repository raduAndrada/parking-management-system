package ro.upet.parking.system.management.business.impl.parking;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import ro.upet.parking.system.management.data.api.parking.ParkingEntity;
import ro.upet.parking.system.management.model.parking.Parking;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author Andrada
 * Mapper for the parking entity and model
 */
@Slf4j
public class ParkingMapper {


	private static final ModelMapper MAPPER = new ModelMapper();
	
	/**
	 * @param parking model for the parking
	 * @return the corresponding entity
	 */
	public static ParkingEntity toParkingEntity(final Parking parking) {
		final String [] openingHours = validateOpenHours(parking.getOpensAt(), parking.getClosesAt());
		parking.setOpensAt(openingHours[0]);
		parking.setClosesAt(openingHours[1]);
		return MAPPER.map(parking, ParkingEntity.class);
	}

	/**
	 * @param entity database level parking
	 * @return the model for the entity
	 */
	public static Parking toParking(final ParkingEntity entity) {
		return MAPPER.map(entity, Parking.class);
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
			log.info("Invalid hour introduced by the user: {}, defaulting to: {}", times[0], correction);
			return "00:00";		
		}
		if (times[1] > 59 || times[1] < 0) {
			log.info("Invalid minutes introduced by the user: {}, defaulting to hh:00", times[1]);
			return times[0] + ":00";
		}
		return times[0] + ":" + times[1];
	}
}
