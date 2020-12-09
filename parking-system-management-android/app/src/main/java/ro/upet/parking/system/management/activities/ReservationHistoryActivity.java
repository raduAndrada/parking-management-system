package ro.upet.parking.system.management.activities;

import android.os.Bundle;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.upet.parking.system.management.R;
import ro.upet.parking.system.management.activities.common.BaseUtils;
import ro.upet.parking.system.management.activities.common.MenuHelper;
import ro.upet.parking.system.management.adaptors.ReservationAdapter;
import ro.upet.parking.system.management.model.ImtReservation;
import ro.upet.parking.system.management.model.Reservation;
import ro.upet.parking.system.management.services.ReservationService;

import static ro.upet.parking.system.management.activities.common.StringConstants.RESERVATIONS_URL;
import static ro.upet.parking.system.management.activities.common.StringConstants.SERVER_BASE_ADDRESS;

public class ReservationHistoryActivity extends MenuHelper {

    private final List<Reservation> reservationList = Lists.newArrayList();
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RESERVATIONS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static final ReservationService service = retrofit.create(ReservationService.class);

    private ReservationAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_history);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new ReservationAdapter(ReservationHistoryActivity.this, reservationList);
        ListView listView = (ListView) findViewById(R.id.reservation_list_id);
        listView.setAdapter(adapter);

        service.getAll(adapter.getUsername()).enqueue(new Callback<List<ImtReservation>>() {
            @Override
            public void onResponse(Call<List<ImtReservation>> call, Response<List<ImtReservation>> response) {
                response.body()
                        .stream()
                        .forEach(r -> reservationList.add(ImtReservation.builder()
                                                                .from(r)
                                                                .startTime(BaseUtils.displayDateTime(BaseUtils.extractDateFromInstantFormat(r.getStartTime())))
                                                                .endTime(BaseUtils.displayDateTime(BaseUtils.extractDateFromInstantFormat(r.getEndTime())))
                                                                .build()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ImtReservation>> call, Throwable t) {

            }
        });
        adapter.notifyDataSetChanged();
    }
}