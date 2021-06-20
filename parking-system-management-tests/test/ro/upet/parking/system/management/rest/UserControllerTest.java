package ro.upet.parking.system.management.rest;


import com.stripe.model.Card;
import com.stripe.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ro.upet.parking.system.management.business.api.user.UserService;
import ro.upet.parking.system.management.business.api.vehicle.VehicleService;
import ro.upet.parking.system.management.model.user.User;
import ro.upet.parking.system.management.model.user.UserCreate;
import ro.upet.parking.system.management.model.user.UserUpdate;
import ro.upet.parking.system.management.model.vehicle.Vehicle;
import ro.upet.parking.system.management.rest.stripe.UserCreateStripeService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ro.upet.parking.system.management.rest.RestUtil.asJsonString;
import static ro.upet.parking.system.management.util.TestDataBuilder.buildUser;
import static ro.upet.parking.system.management.util.TestDataBuilder.buildVehicle;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private static final String USERS_URI = "/v1/users";
    private static final String USERS_USERNAME_URI = USERS_URI + "/user/{username}";
    private static final String LOGIN_URI = USERS_URI + "/login";
    private static final String CUSTOMER_CREATE_URI = USERS_URI + "/customer-create";
    private static final String CUSTOMER_UPDATE_URI = USERS_URI + "/customer-update";

    private static String USERNAME_1 = "Andrada";
    private static String EMAIL_1 = "email1@test.com";
    private static String PASSWORD_1 = "Password1";
    private static String LICENCE_PLATE_1 = "XX10YYY";
    private static String NAME_1 = "Name1";

    @MockBean
    UserService userService;

    @MockBean
    VehicleService vehicleService;

    @MockBean
    UserCreateStripeService stripeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetByUsername_success() throws Exception {
        when(userService.getByUsername(USERNAME_1)).thenReturn(buildUser());

        mockMvc.perform(get(USERS_USERNAME_URI, USERNAME_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(USERNAME_1))
                .andExpect(jsonPath("$.email").value(EMAIL_1));
    }

    @Test
    public void testLoginWithUsername_success() throws Exception {
        final User user = buildUser(USERNAME_1, EMAIL_1);
        when(userService.loginWithUsernameAndPassword(USERNAME_1, PASSWORD_1)).thenReturn(user);

        mockMvc.perform(post(LOGIN_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(USERNAME_1))
                .andExpect(jsonPath("$.email").value(EMAIL_1));
    }

    @Test
    public void testLoginWithEmail_success() throws Exception {
        final User user = buildUser(USERNAME_1, EMAIL_1);
        when(userService.loginWithEmailAndPassword(EMAIL_1, PASSWORD_1)).thenReturn(user);

        mockMvc.perform(post(LOGIN_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(USERNAME_1))
                .andExpect(jsonPath("$.email").value(EMAIL_1));
    }

    @Test
    public void testCreateCustomer_success() throws Exception {
        final Vehicle vehicle = buildVehicle(LICENCE_PLATE_1);
        vehicle.setUser(buildUser(USERNAME_1, EMAIL_1));
        final UserCreate userCreate = buildUserCreate(vehicle);
        final Customer customer = new Customer();
        customer.setEmail(EMAIL_1);
        customer.setId(EMAIL_1);

        when(vehicleService.add(any(Vehicle.class))).thenReturn(vehicle);
        when(stripeService.createCustomer(EMAIL_1, NAME_1)).thenReturn(customer);
        when(stripeService.createCard(EMAIL_1, userCreate)).thenReturn(new Card());

        mockMvc.perform(post(CUSTOMER_CREATE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userCreate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(USERNAME_1))
                .andExpect(jsonPath("$.email").value(EMAIL_1));
    }

    @Test
    public void testUpdateUser_success() throws Exception {
        final UserUpdate userUpdate = buildUserUpdate();
        when(userService.update(userUpdate)).thenReturn(buildUser(USERNAME_1, EMAIL_1));

        mockMvc.perform(put(CUSTOMER_UPDATE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(USERNAME_1))
                .andExpect(jsonPath("$.email").value(EMAIL_1));
    }

    private static UserCreate buildUserCreate(final Vehicle vehicle){
        return UserCreate.builder()
                .vehicle(vehicle)
                .creditCardCCV("CCV")
                .creditCardExpMonth("12")
                .creditCardNumber("testNumber")
                .build();
    }

    private static UserUpdate buildUserUpdate(){
        return UserUpdate.builder()
                .username(USERNAME_1)
                .email(EMAIL_1)
                .password(PASSWORD_1)
                .passwordConfirm(PASSWORD_1)
                .build();
    }
}
