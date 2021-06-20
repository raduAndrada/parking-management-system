package ro.upet.parking.system.management.activities;

import android.content.Context;
import android.content.SharedPreferences;
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
import ro.upet.parking.system.management.R;
import ro.upet.parking.system.management.activities.common.BaseUtils;
import ro.upet.parking.system.management.activities.common.MenuHelper;
import ro.upet.parking.system.management.adaptors.ReservationAdapter;
import ro.upet.parking.system.management.model.Reservation;
import ro.upet.parking.system.management.services.ReservationService;
import ro.upet.parking.system.management.services.RetrofitServicesInitializer;

import static ro.upet.parking.system.management.activities.common.StringConstants.BASE_URL;
import static ro.upet.parking.system.management.activities.common.StringConstants.SHARED_PREFERENCES;

public class ReservationHistoryActivity extends MenuHelper {

    private final List<Reservation> reservationList = Lists.newArrayList();
    private  ReservationService service;

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
        final SharedPreferences sharedPref = this.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        RetrofitServicesInitializer retrofitServicesInitializer = new RetrofitServicesInitializer(sharedPref.getString(BASE_URL, "none"));
        service = retrofitServicesInitializer.getReservationService();

        service.getAll(adapter.getUsername()).enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                response.body()
                        .stream()
                        .forEach(r -> {
                            r.setStartTime(BaseUtils.displayDateTime(BaseUtils.extractDateFromInstantFormat(r.getStartTime())));
                            r.setEndTime(BaseUtils.displayDateTime(BaseUtils.extractDateFromInstantFormat(r.getEndTime())));
                            reservationList.add(r);
                        });
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {

            }
        });
        adapter.notifyDataSetChanged();
    }
}