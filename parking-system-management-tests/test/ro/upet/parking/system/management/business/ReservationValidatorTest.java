package ro.upet.parking.system.management.business;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;
import ro.upet.parking.system.management.business.api.reservation.ReservationValidator;
import ro.upet.parking.system.management.business.reservation.ReservationValidatorImpl;
import ro.upet.parking.system.management.data.impl.reservation.ReservationRepository;
import ro.upet.parking.system.management.model.base.ReservationStatus;

import java.time.Instant;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static ro.upet.parking.system.management.util.TestDataBuilder.buildReservationEntity;

@RunWith(SpringRunner.class)
public class ReservationValidatorTest {

    private static final String START_TIME_1 = "2021-04-03T10:15:30.00Z";
    private static final String START_TIME_2 = "2021-04-03T09:15:30.00Z";
    private static final String START_TIME_3 = "2021-04-03T09:15:30.00Z";
    private static final String START_TIME_4 = "2021-04-03T10:25:30.00Z";
    private static final String START_TIME_5 = "2021-04-03T09:25:30.00Z";

    private static final String END_TIME_1 = "2021-04-03T11:15:30.00Z";
    private static final String END_TIME_2 = "2021-04-03T10:35:30.00Z";
    private static final String END_TIME_3 = "2021-04-03T12:15:30.00Z";
    private static final String END_TIME_4 = "2021-04-03T10:45:30.00Z";
    private static final String END_TIME_5 = "2021-04-03T10:45:30.00Z";

    private static final Instant START_TIME_INSTANT = Instant.parse(START_TIME_1);
    private static final Instant END_TIME_INSTANT = Instant.parse(END_TIME_1);


    ReservationValidator validator;

    @Mock
    ReservationRepository reservationRepository;

    @Before
    public void setUp() {
        validator = new ReservationValidatorImpl(reservationRepository);
    }

    @Test
    public void testValidateNoOtherReservations_success() {
        when(reservationRepository.findAllByParkingSpotId(any(Long.class))).thenReturn(ImmutableList.of());
        assertTrue(validator.isValidReservation(null, START_TIME_INSTANT, END_TIME_INSTANT));
    }

    @Test
    public void testValidateOneReservationCompleted_success() {
        when(reservationRepository.findAllByParkingSpotId(null))
                .thenReturn(ImmutableList.of(buildReservationEntity(START_TIME_1, END_TIME_1, ReservationStatus.COMPLETED)));
        assertTrue(validator.isValidReservation(null, START_TIME_INSTANT, END_TIME_INSTANT));
    }

    @Test
    public void testValidate_failCase1() {
        when(reservationRepository.findAllByParkingSpotId(null))
                .thenReturn(ImmutableList.of(buildReservationEntity(START_TIME_2, END_TIME_2, ReservationStatus.PENDING)));
        assertFalse(validator.isValidReservation(null, START_TIME_INSTANT, END_TIME_INSTANT));
    }

    @Test
    public void testValidate_failCase2() {
        when(reservationRepository.findAllByParkingSpotId(null))
                .thenReturn(ImmutableList.of(buildReservationEntity(START_TIME_3, END_TIME_3, ReservationStatus.PENDING)));
        assertFalse(validator.isValidReservation(null, START_TIME_INSTANT, END_TIME_INSTANT));;
    }

    @Test
    public void testValidate_failCase3() {
        when(reservationRepository.findAllByParkingSpotId(null))
                .thenReturn(ImmutableList.of(buildReservationEntity(START_TIME_4, END_TIME_4, ReservationStatus.PENDING)));
        assertFalse(validator.isValidReservation(null, START_TIME_INSTANT, END_TIME_INSTANT));
    }

    @Test
    public void testValidate_failCase4() {
        when(reservationRepository.findAllByParkingSpotId(null))
                .thenReturn(ImmutableList.of(buildReservationEntity(START_TIME_5, END_TIME_5, ReservationStatus.PENDING)));
        assertFalse(validator.isValidReservation(null, START_TIME_INSTANT, END_TIME_INSTANT));
    }

}
