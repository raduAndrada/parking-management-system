package ro.upet.parking.system.management.data.impl.payment.options;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.upet.parking.system.management.data.api.payment.options.PaymentOptionsEntity;

@Repository
public interface PaymentOptionsRepository extends JpaRepository<PaymentOptionsEntity, Long> {
	
}
