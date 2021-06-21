package ro.upet.parking.system.management.rest;


import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;
import ro.upet.parking.system.management.business.api.reservation.ReservationService;
import ro.upet.parking.system.management.model.base.ReservationStatus;
import ro.upet.parking.system.management.model.reservation.ReservationCreate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ro.upet.parking.system.management.rest.RestUtil.asJsonString;
import static ro.upet.parking.system.management.util.TestDataBuilder.buildReservation;
import static ro.upet.parking.system.management.util.TestDataBuilder.buildReservationNext;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

    private static final String RESERVATION_URI = "/v1/reservations";
    private static final String RESERVATION_USER_USERNAME_URI = RESERVATION_URI + "/user/{username}";
    private static final String RESERVATION_CREATE_URI = RESERVATION_URI + "/create";
    private static final String RESERVATION_NEXT_USERNAME_URI = RESERVATION_URI + "/reservation-next/{username}";
    private static final String RESERVATION_CLAIM_URI = RESERVATION_URI + "/claim/{reservationId}";
    private static final String RESERVATION_START_URI = RESERVATION_URI + "/start/{reservationId}";
    private static final String RESERVATION_COMPLETE_URI = RESERVATION_URI + "/complete/{reservationId}";

    private static String USERNAME_1 = "Andrada";
    private static final String START_TIME_1 = "2021-04-03T10:15:30.00Z";
    ;
    private static final String END_TIME_1 = "2021-04-03T11:15:30.00Z";


    private static String EMAIL_1 = "email1@test.com";
    private static String PASSWORD_1 = "Password1";
    private static String LICENCE_PLATE_1 = "XX10YYY";
    private static String NAME_1 = "Name1";

    @MockBean
    ReservationService reservationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetListByUsername_success() throws Exception {
        val reservations = ImmutableList.of(buildReservation(START_TIME_1, END_TIME_1, ReservationStatus.PENDING));
        when(reservationService.findAllForUserByUsername(USERNAME_1))
                .thenReturn(reservations);

        mockMvc.perform(get(RESERVATION_USER_USERNAME_URI, USERNAME_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].parkingSpot.parkingName").value("Parking1"))
                .andExpect(jsonPath("$[0].cost").value("10"));
    }

    @Test
    public void testCreateReservation_success() throws Exception {
        final ReservationCreate reservationCreate = buildReservationCreate();
        when(reservationService.createReservation(any(ReservationCreate.class)))
                .thenReturn(buildReservation(START_TIME_1, END_TIME_1, ReservationStatus.PENDING));

        mockMvc.perform(post(RESERVATION_CREATE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(reservationCreate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservationStatus").value("PENDING"))
                .andExpect(jsonPath("$.cost").value("10"));
    }

    @Test
    public void testGetReservationNext_success() throws Exception {
        when(reservationService.getReservationNext(USERNAME_1)).thenReturn(buildReservationNext());

        mockMvc.perform(get(RESERVATION_NEXT_USERNAME_URI, USERNAME_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.days").value("0"))
                .andExpect(jsonPath("$.hours").value("1"))
                .andExpect(jsonPath("$.minutes").value("30"))
                .andExpect(jsonPath("$.durationHours").value("1"))
                .andExpect(jsonPath("$.durationMinutes").value("30"));
    }


    @Test
    public void testClaimReservation_success() throws Exception {
        when(reservationService.claim(1L)).thenReturn(buildReservation(START_TIME_1, END_TIME_1, ReservationStatus.CLAIMED));

        mockMvc.perform(put(RESERVATION_CLAIM_URI, 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservationStatus").value("CLAIMED"));
    }

    @Test
    public void testStartReservation_success() throws Exception {
        when(reservationService.start(1L)).thenReturn(buildReservation(START_TIME_1, END_TIME_1, ReservationStatus.ONGOING));

        mockMvc.perform(put(RESERVATION_START_URI, 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservationStatus").value("ONGOING"));
    }

    @Test
    public void testCompleteReservation_success() throws Exception {
        when(reservationService.complete(1L)).thenReturn(buildReservation(START_TIME_1, END_TIME_1, ReservationStatus.COMPLETED));

        mockMvc.perform(put(RESERVATION_COMPLETE_URI, 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservationStatus").value("COMPLETED"));
    }


    private static ReservationCreate buildReservationCreate() {
        return ReservationCreate.builder()
                .username("Andrada")
                .startTime(START_TIME_1)
                .endTime(END_TIME_1)
                .parkingName("Parking1")
                .build();
    }

}
