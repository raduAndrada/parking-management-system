package ro.upet.parking.system.management.model.payment.options;

import java.time.Instant;
import java.time.LocalDate;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ro.upet.parking.system.management.model.base.BaseModel;
import ro.upet.parking.system.management.model.base.PaymentStatus;


/**
 * @author Andrada
 * Payment Options model
 */
@Value.Immutable
@JsonSerialize(as = ImtPaymentOptions.class)
@JsonDeserialize(builder = ImtPaymentOptions.Builder.class)
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
