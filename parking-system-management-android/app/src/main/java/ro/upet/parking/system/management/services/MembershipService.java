package ro.upet.parking.system.management.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ro.upet.parking.system.management.model.Membership;
import ro.upet.parking.system.management.model.MembershipCreate;

public interface MembershipService extends BaseService<Membership> {

    final static String  USER_PATH = "/user/{userId}";
    final static String  CREATE_PATH = "/create";

    @GET(USER_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<List<Membership>> getByUserId(@Path("userId") final Long userId);

    @POST(CREATE_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<Membership> create(@Body final MembershipCreate body);

}
