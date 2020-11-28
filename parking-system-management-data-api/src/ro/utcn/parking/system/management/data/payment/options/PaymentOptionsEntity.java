package ro.utcn.parking.system.management.data.payment.options;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ro.utcn.parking.system.management.model.base.PaymentStatus;

@Entity
public class PaymentOptionsEntity {
	/**
	 *  identifier for the entity
	 */
	@Id
	@GeneratedValue
	Long id;
	
	/**
	 *  unique code for the entity
	 */
	@Id
	String code;	

	/**
	 *  creation time
	 */
	Instant createdAt;

	/**
	 *  last update time
	 */
	Instant updatedAt;
	
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
}
