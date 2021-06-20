package ro.upet.parking.system.management.services;


import lombok.Getter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import static ro.upet.parking.system.management.activities.common.StringConstants.PARKINGS_URL;
import static ro.upet.parking.system.management.activities.common.StringConstants.RESERVATIONS_URL;
import static ro.upet.parking.system.management.activities.common.StringConstants.USERS_URL;

@Getter
public class RetrofitServicesInitializer {

    private String baseUrl;
    private UserService userService;
    private ReservationService reservationService;
    private ParkingService parkingService;

    public RetrofitServicesInitializer(String baseUrl) {
        this.baseUrl = baseUrl;
        initParkingSpotService();
        initUserService();
        initReservationService();
    }

    private void initUserService(){
        final Retrofit retrofit = getRetrofit(USERS_URL);
        userService = retrofit.create(UserService.class);
    }

    private void initReservationService(){
        final Retrofit retrofit = getRetrofit(RESERVATIONS_URL);
        reservationService = retrofit.create(ReservationService.class);
    }

    private void initParkingSpotService() {
        final Retrofit retrofit = getRetrofit(PARKINGS_URL);
        parkingService = retrofit.create(ParkingService.class);
    }

    private Retrofit getRetrofit(final String specificPath){
       return new Retrofit.Builder()
                .baseUrl(baseUrl + specificPath)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
