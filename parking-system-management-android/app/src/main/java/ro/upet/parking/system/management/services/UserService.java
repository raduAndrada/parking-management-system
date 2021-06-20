package ro.upet.parking.system.management.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ro.upet.parking.system.management.model.User;
import ro.upet.parking.system.management.model.UserCreate;
import ro.upet.parking.system.management.model.UserUpdate;

import static ro.upet.parking.system.management.activities.common.StringConstants.USERNAME;

public interface UserService extends BaseService {

    static final String CUSTOMER_CREATE_PATH = "customer-create";
    static final String CUSTOMER_UPDATE_PATH = "customer-update";
    static final String LOGIN_PATH = "login";


    @GET(USER_USERNAME_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<User> getUserByUsername (@Path(USERNAME) final String username);

    @POST(CUSTOMER_CREATE_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<User> createCustomer (@Body final UserCreate userCreate);

    @POST(LOGIN_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<User> login (@Body final User user);

    @PUT(CUSTOMER_UPDATE_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<User> updateCustomer(@Body final UserUpdate userUpdate);

}
