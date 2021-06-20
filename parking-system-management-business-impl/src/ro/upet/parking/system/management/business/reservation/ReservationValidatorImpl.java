package ro.upet.parking.system.management.business.reservation;

import org.springframework.stereotype.Service;
import ro.upet.parking.system.management.business.api.reservation.ReservationValidator;
import ro.upet.parking.system.management.data.api.reservation.ReservationEntity;
import ro.upet.parking.system.management.data.impl.reservation.ReservationRepository;
import ro.upet.parking.system.management.model.base.ReservationStatus;

import java.time.Instant;
import java.util.List;

/**
 * @author Andrada
 */
@Service
public class ReservationValidatorImpl implements ReservationValidator {

	private final ReservationRepository reservationRepo;

	public ReservationValidatorImpl(ReservationRepository reservationRepo) {
		this.reservationRepo = reservationRepo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValidReservation(final Long parkingSpotId, final Instant startTime, final Instant endTime) {
		boolean valid = true;
		final List<ReservationEntity> reservationsOnSpot = 
							reservationRepo.findAllByParkingSpotId(parkingSpotId);
		for (ReservationEntity oldReservation : reservationsOnSpot) {

			if (oldReservation.getReservationStatus().equals(ReservationStatus.COMPLETED)) {
				break;
			}
	
			/*   
			 * Reservation 1:           |||||||||||||||||||||
			 * Reservation 2:                    ||||||||||||||||||||
			 */
			if (oldReservation.getStartTime().isBefore(startTime) &&
								oldReservation.getEndTime().isBefore(endTime))
			{
				valid = false;
				break;
			}

			/*   
			 * Reservation 1:           |||||||||||||||||||||||||||||||||||||||
			 * Reservation 2:                    ||||||||||||||||||||
			 */
			if (oldReservation.getStartTime().isBefore(startTime) &&
								oldReservation.getEndTime().isAfter(endTime)) {
				valid = false;
				break;
			}
			

			/*   
			 * Reservation 1:                              |||||
			 * Reservation 2:                    ||||||||||||||||||||
			 */
			if (oldReservation.getStartTime().isAfter(startTime) &&
								oldReservation.getEndTime().isBefore(endTime)) {
				valid = false;
				break;
			}
			

			/*   
			 * Reservation 1:           |||||||||||||||||||||
			 * Reservation 2:        ||||||||||||||||||||
			 */
			if(oldReservation.getStartTime().isAfter(startTime) &&
								oldReservation.getStartTime().isBefore(endTime)) {
				valid = false;
				break;
			}
			
		}
		
		return valid;
	}

}
