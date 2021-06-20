package ro.upet.parking.system.management.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import androidx.appcompat.app.AppCompatActivity;

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
import ro.upet.parking.system.management.model.User;
import ro.upet.parking.system.management.model.UserCreate;
import ro.upet.parking.system.management.model.Vehicle;
import ro.upet.parking.system.management.model.Size;
import ro.upet.parking.system.management.services.RetrofitServicesInitializer;
import ro.upet.parking.system.management.services.UserService;

import static ro.upet.parking.system.management.activities.common.BaseUtils.initCreditCardSpinners;
import static ro.upet.parking.system.management.activities.common.StringConstants.BASE_URL;
import static ro.upet.parking.system.management.activities.common.StringConstants.PASSWORD;
import static ro.upet.parking.system.management.activities.common.StringConstants.SHARED_PREFERENCES;
import static ro.upet.parking.system.management.activities.common.StringConstants.USERNAME;
import static ro.upet.parking.system.management.activities.common.StringConstants.USERS_URL;

public class RegisterActivity extends AppCompatActivity {

    private UserService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TypefaceProvider.registerDefaultIconSets();

        final UserCreate userCreate = UserCreate.builder().build();
        final EditText usernameET = (EditText) findViewById(R.id.register_user_username_id);
        final EditText passwordET = (EditText) findViewById(R.id.register_user_password_id);
        final EditText emailET = (EditText) findViewById(R.id.register_user_email_id);
        final EditText nameET = (EditText) findViewById(R.id.register_user_name_id);
        final EditText phoneET = (EditText) findViewById(R.id.register_user_phone_id);
        final EditText licencePlateET = (EditText) findViewById(R.id.register_user_vehicle_licence_plate_id);
        final EditText creditCardNumberET = (EditText) findViewById(R.id.register_credit_card_number_id);
        final EditText creditCardCcvET = (EditText) findViewById(R.id.register_credit_card_ccv_id);

        final EditText ipAddressET = (EditText) findViewById(R.id.register_ip_address_id);

        final BootstrapButton registerBtn =(BootstrapButton) findViewById(R.id.register_user_create_btn_id);

        final Spinner creditCardExpMonthSpinner = (Spinner) findViewById(R.id.register_credit_card_exp_month_id);
        initCreditCardSpinners(this, userCreate, creditCardExpMonthSpinner, R.array.months_array);

        final Spinner creditCardExpYearSpinner = (Spinner) findViewById(R.id.register_credit_card_exp_year_id);
        initCreditCardSpinners(this, userCreate, creditCardExpYearSpinner, R.array.years_array);

        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        registerBtn.setOnClickListener(view -> {
            final String ipAddress = ipAddressET.getText().toString();
            editor.putString(BASE_URL,ipAddress);
            RetrofitServicesInitializer retrofitServicesInitializer = new RetrofitServicesInitializer(ipAddress);
            service = retrofitServicesInitializer.getUserService();

            userCreate.setVehicle(Vehicle.builder()
                                .licencePlate(licencePlateET.getText().toString())
                                .size(Size.MEDIUM)
                                .user(User.builder()
                                        .username(usernameET.getText().toString())
                                        .email(emailET.getText().toString())
                                        .name(nameET.getText().toString())
                                        .phoneNumber(phoneET.getText().toString())
                                        .password(passwordET.getText().toString())
                                        .build())
                                .build());
            userCreate.setCreditCardNumber(creditCardNumberET.getText().toString());
            userCreate .setCreditCardCCV(creditCardCcvET.getText().toString());;

            service.createCustomer(userCreate).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    editor.putString(USERNAME, usernameET.getText().toString());
                    editor.putString(PASSWORD, passwordET.getText().toString());
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

    }



}