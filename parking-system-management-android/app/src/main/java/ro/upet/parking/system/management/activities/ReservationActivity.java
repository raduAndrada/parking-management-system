package ro.upet.parking.system.management.activities;

import android.os.Build;
import android.os.Bundle;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.common.collect.Lists;

import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.upet.parking.system.management.R;
import ro.upet.parking.system.management.activities.common.MenuHelper;
import ro.upet.parking.system.management.model.ImtParking;
import ro.upet.parking.system.management.model.ImtReservation;
import ro.upet.parking.system.management.model.ImtReservationCreate;
import ro.upet.parking.system.management.model.MdfReservationCreate;
import ro.upet.parking.system.management.model.Parking;
import ro.upet.parking.system.management.model.Reservation;
import ro.upet.parking.system.management.services.ParkingService;
import ro.upet.parking.system.management.services.ReservationService;

import static ro.upet.parking.system.management.activities.common.StringConstants.PARKINGS_URL;
import static ro.upet.parking.system.management.activities.common.StringConstants.RESERVATIONS_URL;

public class ReservationActivity extends MenuHelper implements
        AdapterView.OnItemSelectedListener  {


    private  static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(PARKINGS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static final ParkingService parkingService = retrofit.create(ParkingService.class);


    private  static final Retrofit retrofit2 = new Retrofit.Builder()
            .baseUrl(RESERVATIONS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static final ReservationService reservationService = retrofit2.create(ReservationService.class);

    final MdfReservationCreate reservationCreate = MdfReservationCreate.create();
    List<String> parkingList = Lists.newArrayList("abc");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText startTimeET = (EditText) findViewById(R.id.reservation_start_time_id);
        final EditText endTimeET = (EditText) findViewById(R.id.reservation_end_time_id);

        final BootstrapButton reserveBtn = (BootstrapButton) findViewById(R.id.reservation_reserve_btn_id);

        final Spinner parkingNamesSpinner = (Spinner) findViewById(R.id.reservation_parking_names_id);
        ArrayAdapter parkingNamesAdaptor = new ArrayAdapter(ReservationActivity.this,
                android.R.layout.simple_spinner_item,
                parkingList
                );
        parkingNamesAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parkingNamesSpinner.setAdapter(parkingNamesAdaptor);

        parkingService.getAll().enqueue(new Callback<List<ImtParking>>() {

            @Override
            public void onResponse(Call<List<ImtParking>> call, Response<List<ImtParking>> response) {
                parkingList = response.body().stream().map(Parking::getName).collect(Collectors.toList());
                parkingNamesAdaptor.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<ImtParking>> call, Throwable t) {
                //Nothing happens on failure
                Toast.makeText(ReservationActivity.this, "failed to load parking names", Toast.LENGTH_LONG);
            }
        });

        reserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reservationCreate.setStartTime(startTimeET.getText().toString())
                                 .setEndTime(endTimeET.getText().toString());
                reservationService.createReservation(ImtReservationCreate.builder().from(reservationCreate).build()).enqueue(new Callback<ImtReservation>() {
                    @Override
                    public void onResponse(Call<ImtReservation> call, Response<ImtReservation> response) {
                        //TODO
                        Toast.makeText(getApplicationContext(), reservationCreate.toString() , Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ImtReservation> call, Throwable t) {
                        //TODO
                        Toast.makeText(getApplicationContext(), reservationCreate.toString() , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        parkingNamesAdaptor.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        reservationCreate.setParkingName((String) adapterView.getItemAtPosition(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //TODO throw error
    }
}