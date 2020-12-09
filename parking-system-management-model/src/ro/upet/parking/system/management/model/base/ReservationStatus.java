package ro.upet.parking.system.management.model.base;

/**
 * @author Andrada
 * Determine the status of a reservation
 */
public enum ReservationStatus {
	CLAIMED, // vehicle parked successfully
	COMPLETED, // reservation expired 
	ONGOING, // reservation count down timer ongoing
	UNCLAIMED, // reservation count down timer reached 0 before user claimed the spot
	PENDING // will take place in the furture
}
