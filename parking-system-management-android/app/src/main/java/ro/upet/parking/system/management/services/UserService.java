package ro.upet.parking.system.management.services;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ro.upet.parking.system.management.model.ImtUser;
import ro.upet.parking.system.management.model.ImtUserCreate;
import ro.upet.parking.system.management.model.User;
import ro.upet.parking.system.management.model.UserCreate;

public interface UserService extends BaseService {

    static final String CUSTOMER_CREATE_PATH = "customer-create";
    static final String LOGIN_PATH = "login";

    @POST(CUSTOMER_CREATE_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<ImtUser> createCustomer (@Body final ImtUserCreate userCreate);

    @POST(LOGIN_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<ImtUser> login (@Body final ImtUser user);

}
