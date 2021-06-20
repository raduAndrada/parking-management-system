package ro.upet.parking.system.management.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.upet.parking.system.management.R;
import ro.upet.parking.system.management.activities.common.MenuHelper;
import ro.upet.parking.system.management.model.User;
import ro.upet.parking.system.management.model.UserCreate;
import ro.upet.parking.system.management.model.UserUpdate;
import ro.upet.parking.system.management.model.Vehicle;
import ro.upet.parking.system.management.model.UserCreate;
import ro.upet.parking.system.management.model.User;
import ro.upet.parking.system.management.model.UserCreate;
import ro.upet.parking.system.management.services.RetrofitServicesInitializer;
import ro.upet.parking.system.management.services.UserService;

import static ro.upet.parking.system.management.activities.common.BaseUtils.handleNulls;
import static ro.upet.parking.system.management.activities.common.BaseUtils.initCreditCardSpinners;
import static ro.upet.parking.system.management.activities.common.StringConstants.BASE_URL;
import static ro.upet.parking.system.management.activities.common.StringConstants.SHARED_PREFERENCES;
import static ro.upet.parking.system.management.activities.common.StringConstants.USERNAME;
import static ro.upet.parking.system.management.activities.common.StringConstants.USERS_URL;

public class UserProfileActivity extends MenuHelper {

    private UserService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final SharedPreferences sharedPref = this.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        final String username= sharedPref.getString(USERNAME, "none");

        final UserCreate userCreate = UserCreate.builder().build();
        final EditText passwordET = (EditText) findViewById(R.id.user_profile_password_id);
        final EditText passwordConfirmET = (EditText) findViewById(R.id.user_profile_password_confirm_id);
        final EditText emailET = (EditText) findViewById(R.id.user_profile_user_email_id);
        final EditText phoneET = (EditText) findViewById(R.id.user_profile_phone_number_id);
        final EditText licencePlateET = (EditText) findViewById(R.id.user_profile_vehicle_licence_plate_id);
        final EditText creditCardNumberET = (EditText) findViewById(R.id.user_profile_credit_card_number_id);
        final EditText creditCardCcvET = (EditText) findViewById(R.id.user_profile_credit_card_ccv_id);

        final BootstrapButton saveBtn =(BootstrapButton) findViewById(R.id.user_profile_save_btn_id);

        final Spinner creditCardExpMonthSpinner = (Spinner) findViewById(R.id.user_profile_credit_card_exp_month_id);
        initCreditCardSpinners(this, userCreate, creditCardExpMonthSpinner, R.array.months_array);

        final Spinner creditCardExpYearSpinner = (Spinner) findViewById(R.id.user_profile_credit_card_exp_year_id);
        initCreditCardSpinners(this, userCreate, creditCardExpYearSpinner, R.array.years_array);

        RetrofitServicesInitializer retrofitServicesInitializer = new RetrofitServicesInitializer(sharedPref.getString(BASE_URL, "none"));
        service = retrofitServicesInitializer.getUserService();

        service.getUserByUsername(username).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                final User user = response.body();
                emailET.setText(handleNulls(user.getEmail(), "") );
                phoneET.setText(handleNulls(user.getPhoneNumber(), ""));
                licencePlateET.setText(handleNulls(user.getName(), ""));
                // TODO details about credit card ?

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //TODO handle this
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserUpdate userUpdate = UserUpdate.builder()
                        .creditCardCCV(creditCardCcvET.getText().toString())
                        .creditCardExpMonth(userCreate.getCreditCardExpMonth())
                        .creditCardExpYear(userCreate.getCreditCardExpYear())
                        .password( passwordET.getText().toString())
                        .passwordConfirm(passwordConfirmET.getText().toString())
                        .licencePlate(licencePlateET.getText().toString())
                        .phoneNumber(phoneET.getText().toString())
                        .email(emailET.getText().toString())
                        .username(username)
                        .build();
                service.updateCustomer(userUpdate).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(UserProfileActivity.this, "Successfully Updated Profile", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(UserProfileActivity.this, "Failed to update", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}