package ro.upet.parking.system.management.data.api.payment.options;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.model.base.PaymentStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "payment_options")
public class PaymentOptionsEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  common fields
	 */
	private BaseEntity base;
	/**
	 *  status of the payment
	 */
	PaymentStatus paymentStatus;
	
	/**
	 *  start date of the  availability of the payment (for memberships)
	 */
	LocalDate startPeriod;
	
	/**
	 *  end date of the  availability of the payment (for memberships)
	 */
	LocalDate endPeriod;
	
	/**
	 * id for stripe
	 */
	String stripeId;
	
	/**
	 * owner of this payment options
	 */
	@OneToOne
	UserEntity user;


	
}
