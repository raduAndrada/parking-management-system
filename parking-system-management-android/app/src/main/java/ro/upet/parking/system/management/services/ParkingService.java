package ro.upet.parking.system.management.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import ro.upet.parking.system.management.model.Membership;
import ro.upet.parking.system.management.model.MembershipCreate;
import ro.upet.parking.system.management.model.Parking;
import ro.upet.parking.system.management.model.ParkingCreate;

public interface ParkingService extends BaseService<Parking> {

    static final String CONFIGURE_PATH = "/configure";

    @POST(CONFIGURE_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<Parking> configure(@Body final ParkingCreate body);
}
