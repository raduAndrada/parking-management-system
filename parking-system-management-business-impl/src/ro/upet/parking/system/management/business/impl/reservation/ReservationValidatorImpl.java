package ro.upet.parking.system.management.business.impl.reservation;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ro.upet.parking.system.management.business.api.reservation.ReservationValidator;
import ro.upet.parking.system.management.data.api.reservation.ReservationEntity;
import ro.upet.parking.system.management.data.impl.reservation.ReservationRepository;

/**
 * @author Andrada
 */
@Service
public class ReservationValidatorImpl implements ReservationValidator {

	@Inject
	private ReservationRepository reservationRepo;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validate(ReservationEntity entity) {
		boolean valid = true;
		final List<ReservationEntity> reservationsOnSpot = 
							reservationRepo.findAllByParkingSpotNumber(entity.getParkingSpot().getNumber());
		for (ReservationEntity oldReservation : reservationsOnSpot) {
			
	
			/*   
			 * Reservation 1:           |||||||||||||||||||||
			 * Reservation 2:                    ||||||||||||||||||||
			 */
			if (oldReservation.getStartTime().isBefore(entity.getStartTime()) &&
								oldReservation.getEndTime().isBefore(entity.getEndTime()))
			{
				valid = false;
			}

			/*   
			 * Reservation 1:           |||||||||||||||||||||||||||||||||||||||
			 * Reservation 2:                    ||||||||||||||||||||
			 */
			if (oldReservation.getStartTime().isBefore(entity.getStartTime()) && 
								oldReservation.getEndTime().isAfter(entity.getEndTime())) {
				valid = false;
			}
			

			/*   
			 * Reservation 1:                              |||||
			 * Reservation 2:                    ||||||||||||||||||||
			 */
			if (oldReservation.getStartTime().isAfter(entity.getStartTime()) && 
								oldReservation.getEndTime().isBefore(entity.getEndTime())) {
				valid = false;
			}
			

			/*   
			 * Reservation 1:           |||||||||||||||||||||
			 * Reservation 2:        ||||||||||||||||||||
			 */
			if(oldReservation.getStartTime().isAfter(entity.getStartTime()) &&
								oldReservation.getStartTime().isBefore(entity.getEndTime())) {
				valid = false;
			}
			
		}
		
		return valid;
	}

}
