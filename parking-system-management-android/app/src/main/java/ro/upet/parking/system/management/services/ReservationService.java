package ro.upet.parking.system.management.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import ro.upet.parking.system.management.model.ImtParking;
import ro.upet.parking.system.management.model.ImtReservation;
import ro.upet.parking.system.management.model.ImtReservationCreate;
import ro.upet.parking.system.management.model.Reservation;
import ro.upet.parking.system.management.model.ReservationCreate;
import ro.upet.parking.system.management.model.User;
import ro.upet.parking.system.management.model.UserCreate;

public interface ReservationService extends BaseService{

    static final String RESERVATION_CREATE_PATH = "create";

    @POST(RESERVATION_CREATE_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<ImtReservation> createReservation(@Body final ImtReservationCreate reservationCreate);

    @GET(LIST_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<List<ImtReservation>> getAll();
}
