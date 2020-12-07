package ro.upet.parking.system.management.model;

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
