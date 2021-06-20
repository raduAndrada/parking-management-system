package ro.upet.parking.system.management.model.payment.options;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.upet.parking.system.management.model.base.BaseModel;
import ro.upet.parking.system.management.model.base.PaymentStatus;

import java.time.LocalDate;


/**
 * @author Andrada
 * Payment Options model
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaymentOptions extends BaseModel{
	
	/**
	 * @return status of the payment
	 */
	PaymentStatus paymentStatus;
	
	/**
	 * @return start date of the  availability of the payment (for memberships)
	 */
	LocalDate startPeriod;
	
	/**
	 * @return end date of the  availability of the payment (for memberships)
	 */
	LocalDate endPeriod;
	
	/**
	 * @return user id
	 */
	Long userId;
	
	/**
	 * @return user's unique code
	 */
	String userCode;
}
