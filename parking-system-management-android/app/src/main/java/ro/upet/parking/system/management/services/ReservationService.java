package ro.upet.parking.system.management.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ro.upet.parking.system.management.model.ImtReservation;
import ro.upet.parking.system.management.model.ImtReservationCreate;
import ro.upet.parking.system.management.model.ImtReservationNext;
import ro.upet.parking.system.management.model.ImtUserUpdate;

public interface ReservationService extends BaseService{

    static final String RESERVATION_CREATE_PATH = "create";
    static final String RESERVATION_CLAIM_PATH = "claim/{reservationId}";
    static final String RESERVATION_NEXT_PATH = "reservation-next/{username}";


    @POST(RESERVATION_CREATE_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<ImtReservation> createReservation(@Body final ImtReservationCreate reservationCreate);

    @GET(USER_USERNAME_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<List<ImtReservation>> getAll(@Path("username") final String username);

    @GET(RESERVATION_NEXT_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<ImtReservationNext> getReservationNext(@Path("username") final String username);

    @DELETE(ID_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<ImtReservation> deleteReservationById(@Path ("id") final Long id);

    @PUT(RESERVATION_CLAIM_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<ImtReservation> claimReservation(@Path ("reservationId") final Long reservationId);
}
