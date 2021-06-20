package ro.upet.parking.system.management.rest;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ro.upet.parking.system.management.business.api.parking.ParkingService;
import ro.upet.parking.system.management.model.parking.ParkingCreate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ro.upet.parking.system.management.rest.RestUtil.asJsonString;
import static ro.upet.parking.system.management.util.TestDataBuilder.buildParkingCreate;

@SpringBootTest
@AutoConfigureMockMvc
public class ParkingControllerTest {

    private static final String PARKINGS_URI = "/v1/parkings";
    private static final String CONFIGURE_URI = PARKINGS_URI + "/configure";


    @MockBean
    ParkingService parkingService;


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetByUsername_success() throws Exception {
        final ParkingCreate parkingCreate = buildParkingCreate('a', 'g', 3,5);
        when(parkingService.configureParking(any(ParkingCreate.class))).thenReturn(parkingCreate);

        mockMvc.perform(post(CONFIGURE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(parkingCreate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfLevels").value(3));
    }


}
