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
import ro.upet.parking.system.management.model.Reservation;
import ro.upet.parking.system.management.model.ReservationCreate;
import ro.upet.parking.system.management.model.ReservationNext;
import ro.upet.parking.system.management.model.UserUpdate;

public interface ReservationService extends BaseService{

    String RESERVATION_CREATE_PATH = "create";
    String RESERVATION_CLAIM_PATH = "claim/{reservationId}";
    String RESERVATION_START_PATH = "start/{reservationId}";
    String RESERVATION_COMPLETE_PATH = "complete/{reservationId}";
    String RESERVATION_NEXT_PATH = "reservation-next/{username}";


    @POST(RESERVATION_CREATE_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<Reservation> createReservation(@Body final ReservationCreate reservationCreate);

    @GET(USER_USERNAME_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<List<Reservation>> getAll(@Path("username") final String username);

    @GET(RESERVATION_NEXT_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<ReservationNext> getReservationNext(@Path("username") final String username);

    @DELETE(ID_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<Reservation> deleteReservationById(@Path ("id") final Long id);

    @PUT(RESERVATION_CLAIM_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<Reservation> claimReservation(@Path ("reservationId") final Long reservationId);

    @PUT(RESERVATION_START_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<Reservation> startReservation(@Path ("reservationId") final Long reservationId);

    @PUT(RESERVATION_COMPLETE_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<Reservation> completeReservation(@Path ("reservationId") final Long reservationId);
}
