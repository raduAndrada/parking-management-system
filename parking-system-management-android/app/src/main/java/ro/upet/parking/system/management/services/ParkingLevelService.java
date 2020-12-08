package ro.upet.parking.system.management.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import ro.upet.parking.system.management.model.ParkingLevel;

public interface ParkingLevelService extends BaseService {

    static final String PARKING_PATH = "/parking/{parkingId}";

    @GET(PARKING_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<List<ParkingLevel>> getParkingId(@Path("parkingId") final Long parkingId);
}
