package ro.upet.parking.system.management.data.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ro.upet.parking.system.management.data.FlywayConfigurationClass;
import ro.upet.parking.system.management.data.api.payment.options.PaymentOptionsEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.impl.payment.options.PaymentOptionsRepository;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.model.base.PaymentStatus;


public class ITPaymentOptionsRepository extends ITDataTests {

    private static final PaymentStatus PAYMENT_OPTIONS_STATUS1 = PaymentStatus.NOT_PAID;
    private static final PaymentStatus PAYMENT_OPTIONS_STATUS2 = PaymentStatus.PAID;

    private static final LocalDate PAYMENT_OPTIONS_START_PERIOD1 = LocalDate.parse("2020-12-01");
    private static final LocalDate PAYMENT_OPTIONS_START_PERIOD2 = LocalDate.parse("2020-11-30");

    private static final LocalDate PAYMENT_OPTIONS_END_PERIOD1 = LocalDate.parse("2021-12-01");
    private static final LocalDate PAYMENT_OPTIONS_END_PERIOD2 = LocalDate.parse("2021-01-01");

    private static PaymentOptionsEntity PAYMENT_OPTIONS_1 = new PaymentOptionsEntity();
    private static PaymentOptionsEntity PAYMENT_OPTIONS_2 = new PaymentOptionsEntity();

    @Inject
    private PaymentOptionsRepository paymentOptionsRepo;

    @Inject
    private UserRepository userRepo;

    @Before
    public void init() {

        final UserEntity user1 = new UserEntity();
        user1.setEmail("testEmail1");
        user1.setUsername("username1");
        user1.setPassword("password1");

        final UserEntity user2 = new UserEntity();
        user2.setEmail("testEmail2");
        user2.setUsername("username2");
        user2.setPassword("password2");

        PAYMENT_OPTIONS_1.setStartPeriod(PAYMENT_OPTIONS_START_PERIOD1);
        PAYMENT_OPTIONS_1.setEndPeriod(PAYMENT_OPTIONS_END_PERIOD1);
        PAYMENT_OPTIONS_1.setPaymentStatus(PAYMENT_OPTIONS_STATUS1);
        userRepo.save(user1);
        PAYMENT_OPTIONS_1.setUser(user1);

        PAYMENT_OPTIONS_2.setStartPeriod(PAYMENT_OPTIONS_START_PERIOD2);
        PAYMENT_OPTIONS_2.setEndPeriod(PAYMENT_OPTIONS_END_PERIOD2);
        PAYMENT_OPTIONS_2.setPaymentStatus(PAYMENT_OPTIONS_STATUS2);
        userRepo.save(user2);
        PAYMENT_OPTIONS_1.setUser(user2);
    }

    @Test
    public void addPaymentOptions_test_success() {
        val testEntity = paymentOptionsRepo.save(PAYMENT_OPTIONS_1);
        val expectedEntity = paymentOptionsRepo.findById(testEntity.getId());
        assertThat(expectedEntity).isNotNull();
        assertThat(expectedEntity.get().getPaymentStatus()).isEqualTo(PAYMENT_OPTIONS_STATUS1);
    }

    @Test
    public void deletePaymentOptions_test_success() {
        val testEntity = paymentOptionsRepo.save(PAYMENT_OPTIONS_1);
        paymentOptionsRepo.delete(testEntity);
        val expectedEntity = paymentOptionsRepo.findById(testEntity.getId());
        assertThat(expectedEntity.isEmpty());
    }


    @Test
    public void findAllPaymentOptionss_test_success() {
        paymentOptionsRepo.save(PAYMENT_OPTIONS_1);
        paymentOptionsRepo.save(PAYMENT_OPTIONS_2);
        final List<PaymentOptionsEntity> expectedList = paymentOptionsRepo.findAll();
        assertThat(expectedList).isNotNull();
        assertThat(expectedList.size()).isEqualTo(2);
    }


    @Test
    public void updatePaymentOptions_test_success() {
        val testEntity = paymentOptionsRepo.save(PAYMENT_OPTIONS_1);
        testEntity.setPaymentStatus(PAYMENT_OPTIONS_STATUS2);
        testEntity.setUser(PAYMENT_OPTIONS_1.getUser());
        paymentOptionsRepo.save(testEntity);
        val expectedEntity = paymentOptionsRepo.findById(testEntity.getId());
        assertThat(expectedEntity).isNotNull();
        assertThat(expectedEntity.get().getPaymentStatus()).isEqualTo(PAYMENT_OPTIONS_STATUS2);
    }
}

