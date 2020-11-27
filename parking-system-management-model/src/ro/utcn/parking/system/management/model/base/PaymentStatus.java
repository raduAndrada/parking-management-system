package ro.utcn.parking.system.management.model.base;

/**
 * @author Andrada
 * Enum used to determine the status of a payment
 */
public enum PaymentStatus {
	PAID, // payment has been processed successfully 
	PREPAID, // reservation paid in advance
	NOT_PAID, // reservation was not paid
	ERROR // error with the payment occurred
}
