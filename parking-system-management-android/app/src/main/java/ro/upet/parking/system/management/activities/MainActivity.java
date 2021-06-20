package ro.upet.parking.system.management.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.upet.parking.system.management.R;
import ro.upet.parking.system.management.activities.common.MenuHelper;

import ro.upet.parking.system.management.model.Reservation;
import ro.upet.parking.system.management.model.ReservationNext;
import ro.upet.parking.system.management.model.ReservationStatus;
import ro.upet.parking.system.management.services.ReservationService;
import ro.upet.parking.system.management.services.RetrofitServicesInitializer;
import ro.upet.parking.system.management.services.UserService;

import android.os.CountDownTimer;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import static ro.upet.parking.system.management.activities.common.StringConstants.BASE_URL;
import static ro.upet.parking.system.management.activities.common.StringConstants.RESERVATIONS_URL;
import static ro.upet.parking.system.management.activities.common.StringConstants.SHARED_PREFERENCES;
import static ro.upet.parking.system.management.activities.common.StringConstants.USERNAME;
import static ro.upet.parking.system.management.activities.common.StringConstants.USERS_URL;

public class MainActivity extends MenuHelper {

    private ReservationService service ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(null);

        TypefaceProvider.registerDefaultIconSets();

        final SharedPreferences sharedPref = this.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        final String username= sharedPref.getString(USERNAME, "none");
        RetrofitServicesInitializer retrofitServicesInitializer = new RetrofitServicesInitializer(sharedPref.getString(BASE_URL, "none"));
        service = retrofitServicesInitializer.getReservationService();

        final TextView timeTextView = (TextView) findViewById(R.id.main_time_indicator_id);
        final TextView daysTextView = (TextView) findViewById(R.id.main_days_indicator_counter_id);
        final TextView hoursTextView = (TextView) findViewById(R.id.main_hours_indicator_counter_id);
        final TextView minutesTextView = (TextView) findViewById(R.id.main_minutes_indicator_counter_id);

        final BootstrapButton claimBtn = (BootstrapButton) findViewById(R.id.main_claim_btn_id);
        final BootstrapButton removeBtn = (BootstrapButton) findViewById(R.id.main_remove_btn_id);
        final BootstrapButton releaseBtn = (BootstrapButton) findViewById(R.id.main_release_btn_id);

        releaseBtn.setVisibility(View.INVISIBLE);

        service.getReservationNext(username).enqueue(new Callback<ReservationNext>() {
            @Override
            public void onResponse(Call<ReservationNext> call, Response<ReservationNext> response) {
                final ReservationNext rn = response.body();
                if (Objects.nonNull(rn)) {

                    daysTextView.setText(rn.getDays().toString());
                    hoursTextView.setText(rn.getHours().toString());
                    minutesTextView.setText(rn.getMinutes().toString());

                    if (rn.getReservationStatus().equals(ReservationStatus.PENDING)) {
                        claimBtn.setEnabled(false);
                        removeBtn.setEnabled(true);
                    }
                    if (rn.getReservationStatus().equals(ReservationStatus.UNCLAIMED)) {
                        claimBtn.setEnabled(true);
                        removeBtn.setEnabled(false);
                    }
                    claimBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            service.claimReservation(rn.getReservationId()).enqueue(new Callback<Reservation>() {
                                @Override
                                public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                                    Toast.makeText(getApplicationContext(), "Successfully claimed Reservation", Toast.LENGTH_LONG).show();
                                    claimBtn.setEnabled(false);
                                    claimBtn.setText(ReservationStatus.CLAIMED.toString());
                                    removeBtn.setEnabled(false);
                                }

                                @Override
                                public void onFailure(Call<Reservation> call, Throwable t) {

                                }
                            });
                        }
                    });

                    removeBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            service.deleteReservationById(rn.getReservationId()).enqueue(new Callback<Reservation>() {
                                @Override
                                public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                                    Toast.makeText(getApplicationContext(), "Successfully removed Reservation", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(MainActivity.this, ReservationHistoryActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<Reservation> call, Throwable t) {

                                }
                            });
                        }
                    });
                    releaseBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            service.completeReservation(rn.getReservationId()).enqueue(new Callback<Reservation>() {
                                @Override
                                public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                                    Toast.makeText(getApplicationContext(), "Successfully claimed Reservation", Toast.LENGTH_LONG).show();
                                    releaseBtn.setEnabled(false);
                                    releaseBtn.setText(ReservationStatus.COMPLETED.toString());
                                    removeBtn.setEnabled(false);
                                    Intent intent = new Intent(MainActivity.this, ReservationHistoryActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<Reservation> call, Throwable t) {

                                }
                            });
                        }
                    });

                    new CountDownTimer(computeTimerTask(rn.getDays(), rn.getHours(), rn.getMinutes()), 1000) {

                        Integer tempMinutes = rn.getMinutes();
                        Integer tempHours = rn.getHours();
                        Integer tempDays = rn.getDays();
                        Integer tempSeconds = 60;

                        public void onTick(long millisUntilFinished) {
                            tempSeconds--;

                            if (tempSeconds == 0) {
                                tempMinutes--;
                                tempSeconds = 60;
                            }
                            if (tempMinutes == 0) {
                                if (tempHours > 0) {
                                    tempHours--;
                                    tempMinutes = 60;
                                }
                            }
                            if (tempHours == 0) {
                                if (tempDays > 0) {
                                    tempDays--;
                                    tempHours = 24;
                                }
                            }
                            daysTextView.setText(tempDays.toString());
                            hoursTextView.setText(tempHours.toString());
                            minutesTextView.setText(tempMinutes.toString());
                            if (tempDays == 0 && tempHours == 0 && tempMinutes == 15) {
                                claimBtn.setEnabled(true);
                                removeBtn.setEnabled(false);
                            }
                        }

                        public void onFinish() {
                            timeTextView.setText("ONGOING RESERVATION EXPIRES IN: ");
                            daysTextView.setText("0");
                            new CountDownTimer(computeTimerTask(0, rn.getDurationHours(), rn.getDurationMinutes() + 15), 1000) {

                                Integer tempDurationMinutes = rn.getDurationMinutes();
                                Integer tempDurationHours = rn.getDurationHours();
                                Integer tempDurationSeconds = 60;

                                @Override
                                public void onTick(long millisUntilFinished) {
                                    tempDurationSeconds--;

                                    if (tempDurationSeconds == 0) {
                                        tempDurationMinutes--;
                                        tempDurationSeconds = 60;
                                    }
                                    if (tempDurationMinutes == 0) {
                                        if (tempDurationHours > 0) {
                                            tempDurationHours--;
                                            tempDurationMinutes = 60;
                                        }
                                    }
                                    hoursTextView.setText(tempHours.toString());
                                    minutesTextView.setText(tempMinutes.toString());
                                    if ( tempDurationHours == 0 && tempDurationMinutes == 15) {
                                        claimBtn.setEnabled(false);
                                        removeBtn.setEnabled(false);
                                        releaseBtn.setVisibility(View.VISIBLE);
                                        removeBtn.setEnabled(true);
                                    }
                                }

                                @Override
                                public void onFinish() {
                                    Intent intent = new Intent(MainActivity.this, ReservationHistoryActivity.class);
                                    startActivity(intent);
                                }

                            }.start();


                        }
                    }.start();
                } else {
                    timeTextView.setText("NO UPCOMING RESERVATIONS");
                    daysTextView.setVisibility(View.INVISIBLE);
                    hoursTextView.setVisibility(View.INVISIBLE);
                    minutesTextView.setVisibility(View.INVISIBLE);
                    claimBtn.setVisibility(View.INVISIBLE);
                    removeBtn.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ReservationNext> call, Throwable t) {

            }
        });

    }

    private int computeTimerTask(int days, int hours, int minutes) {
        return 24 * 3600 * 1000 * days +  3600 * 1000 * hours +  60 * minutes * 1000;
    }

}