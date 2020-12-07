package ro.upet.parking.system.management.model;

import java.time.Instant;
import java.time.LocalDate;

import org.immutables.value.Value;



import ro.upet.parking.system.management.model.PaymentStatus;


/**
 * @author Andrada
 * Payment Options model
 */
@Value.Immutable
public interface PaymentOptions extends BaseModel{
	
	/**
	 * @return status of the payment
	 */
	PaymentStatus getPaymentStatus();
	
	/**
	 * @return start date of the  availability of the payment (for memberships)
	 */
	LocalDate getStartPeriod();
	
	/**
	 * @return end date of the  availability of the payment (for memberships)
	 */
	LocalDate getEndPeriod();
	
	/**
	 * @return user id
	 */
	Long getUserId();
	
	/**
	 * @return user's unique code
	 */
	String getUserCode();
}
