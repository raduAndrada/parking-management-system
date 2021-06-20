package ro.upet.parking.system.management.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.common.collect.Lists;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.upet.parking.system.management.R;
import ro.upet.parking.system.management.activities.common.BaseUtils;
import ro.upet.parking.system.management.activities.common.MenuHelper;
import ro.upet.parking.system.management.model.Parking;
import ro.upet.parking.system.management.model.Reservation;
import ro.upet.parking.system.management.model.ReservationCreate;
import ro.upet.parking.system.management.services.ParkingService;
import ro.upet.parking.system.management.services.ReservationService;
import ro.upet.parking.system.management.services.RetrofitServicesInitializer;

import static ro.upet.parking.system.management.activities.common.BaseUtils.convertTimeToParsingInstantFormat;
import static ro.upet.parking.system.management.activities.common.BaseUtils.isValidHourAndMinute;
import static ro.upet.parking.system.management.activities.common.StringConstants.BASE_URL;
import static ro.upet.parking.system.management.activities.common.StringConstants.PARKINGS_URL;
import static ro.upet.parking.system.management.activities.common.StringConstants.RESERVATIONS_URL;
import static ro.upet.parking.system.management.activities.common.StringConstants.SHARED_PREFERENCES;
import static ro.upet.parking.system.management.activities.common.StringConstants.USERNAME;

public class ReservationActivity extends MenuHelper implements
        AdapterView.OnItemSelectedListener  {


    private ParkingService parkingService;

    private ReservationService reservationService;

    final ReservationCreate reservationCreate = ReservationCreate.builder().build();
    List<String> parkingList = Lists.newArrayList();

    static TextView selectedDateTV;
    static String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final SharedPreferences sharedPref = this.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        final String username= sharedPref.getString(USERNAME, "none");
        RetrofitServicesInitializer retrofitServicesInitializer = new RetrofitServicesInitializer(sharedPref.getString(BASE_URL, "none"));
        reservationService = retrofitServicesInitializer.getReservationService();
        parkingService = retrofitServicesInitializer.getParkingService();

        final EditText startTimeET = (EditText) findViewById(R.id.reservation_start_time_id);
        final EditText endTimeET = (EditText) findViewById(R.id.reservation_end_time_id);
        final TextView invalidTimeTV = (TextView) findViewById(R.id.reservation_invalid_time_id);
        invalidTimeTV.setVisibility(View.INVISIBLE);

        selectedDateTV  = (TextView) findViewById(R.id.reservation_selected_date_id);

        final BootstrapButton dateBtn = (BootstrapButton) findViewById(R.id.reservation_date_btn_id);
        final BootstrapButton reserveBtn = (BootstrapButton) findViewById(R.id.reservation_reserve_btn_id);

        final Spinner parkingNamesSpinner = (Spinner) findViewById(R.id.reservation_parking_names_id);


        parkingService.getAll().enqueue(new Callback<List<Parking>>() {

            @Override
            public void onResponse(Call<List<Parking>> call, Response<List<Parking>> response) {
                parkingList = response.body().stream().map(Parking::getName).collect(Collectors.toList());
                ArrayAdapter parkingNamesAdaptor = new ArrayAdapter(ReservationActivity.this,
                        android.R.layout.simple_spinner_item,
                        parkingList
                );
                parkingNamesAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                parkingNamesSpinner.setAdapter(parkingNamesAdaptor);
                parkingNamesSpinner.setOnItemSelectedListener(ReservationActivity.this);
            }

            @Override
            public void onFailure(Call<List<Parking>> call, Throwable t) {
                //Nothing happens on failure
                Toast.makeText(ReservationActivity.this, "failed to load parking names", Toast.LENGTH_LONG);
            }
        });

        reserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startTime = startTimeET.getText().toString();
                String endTime = endTimeET.getText().toString();
                if (isValidHourAndMinute(endTime) && isValidHourAndMinute(startTime)) {
                    startTime = convertTimeToParsingInstantFormat(date, startTime);
                    endTime = convertTimeToParsingInstantFormat(date, endTime);
                    reservationCreate.setStartTime(startTime);
                    reservationCreate.setEndTime(endTime);
                    reservationCreate.setUsername(username);
                    reservationService.createReservation(reservationCreate).enqueue(new Callback<Reservation>() {
                        @Override
                        public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                            invalidTimeTV.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Created reservation: " + response.body().toString(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ReservationActivity.this, ReservationHistoryActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Reservation> call, Throwable t) {
                            //TODO
                            Toast.makeText(getApplicationContext(), "Failed to create reservation", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    invalidTimeTV.setVisibility(View.VISIBLE);
                }
            }
        });

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        reservationCreate.setParkingName((String) adapterView.getItemAtPosition(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        if (adapterView.getChildCount() > 0) {
            reservationCreate.setParkingName((String) adapterView.getItemAtPosition(0));
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }



    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            date = BaseUtils.convertDateToParsingInstantFormat(year, month, day);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            selectedDateTV.setText("Date: " + day + "-" + (++month));
            date = BaseUtils.convertDateToParsingInstantFormat(year, month, day);
        }
    }

}