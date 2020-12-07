package ro.upet.parking.system.management.activities;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.common.collect.Lists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.upet.parking.system.management.R;
import ro.upet.parking.system.management.adaptors.ReservationAdapter;
import ro.upet.parking.system.management.model.Reservation;
import ro.upet.parking.system.management.services.ReservationService;

import static ro.upet.parking.system.management.adaptors.BaseAdaptor.SERVER_BASE_ADDRESS;

public class ReservationActivity extends AppCompatActivity {

    private static final String RESERVATIONS_URL = SERVER_BASE_ADDRESS + "v1/reservations";

    private final List<Reservation> reservationList = Lists.newArrayList();
    private  static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RESERVATIONS_URL)
                .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static final ReservationService service = retrofit.create(ReservationService .class);

    private ReservationAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new ReservationAdapter(ReservationActivity.this, reservationList);
        ListView listView = (ListView) findViewById(R.id.reservation_list_id);
        listView.setAdapter(adapter);

        service.getAll().enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                reservationList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {

            }
        });
        adapter.notifyDataSetChanged();
    }
}