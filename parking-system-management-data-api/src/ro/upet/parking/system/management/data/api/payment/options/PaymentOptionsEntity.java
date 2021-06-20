package ro.upet.parking.system.management.data.api.payment.options;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.model.base.PaymentStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "payment_options")
public class PaymentOptionsEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 *  identifier for the entity
	 */
	@Id
	@GeneratedValue		
	private Long id;
	
	/**
	 *  common fields
	 */
	@Embedded
	private BaseEntity base;
	/**
	 *  status of the payment
	 */
	private PaymentStatus paymentStatus;
	
	/**
	 *  start date of the  availability of the payment (for memberships)
	 */
	private LocalDate startPeriod;
	
	/**
	 *  end date of the  availability of the payment (for memberships)
	 */
	private LocalDate endPeriod;
	
	/**
	 * id for stripe
	 */
	private String stripeId;
	
	/**
	 * owner of this payment options
	 */
	@OneToOne(fetch = FetchType.LAZY)
	private UserEntity user;

}
