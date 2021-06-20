package ro.upet.parking.system.management.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.upet.parking.system.management.R;
import ro.upet.parking.system.management.model.User;
import ro.upet.parking.system.management.services.RetrofitServicesInitializer;
import ro.upet.parking.system.management.services.UserService;

import static ro.upet.parking.system.management.activities.common.StringConstants.BASE_URL;
import static ro.upet.parking.system.management.activities.common.StringConstants.PASSWORD;
import static ro.upet.parking.system.management.activities.common.StringConstants.SHARED_PREFERENCES;
import static ro.upet.parking.system.management.activities.common.StringConstants.USERNAME;
import static ro.upet.parking.system.management.activities.common.StringConstants.USERS_URL;

public class LoginActivity extends AppCompatActivity {

    private UserService service;
    private RetrofitServicesInitializer retrofitServicesInitializer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText usernameOrEmailET = (EditText) findViewById(R.id.login_username_id);
        final EditText passwordET = (EditText) findViewById(R.id.login_password_id);
        final EditText ipAddressET = (EditText) findViewById(R.id.ip_address_id);

        final TextView loginFailureTV = (TextView) findViewById(R.id.login_invalid_id);
        final BootstrapButton registerBtn = (BootstrapButton) findViewById(R.id.login_create_account_id);

        final BootstrapButton loginBtn = (BootstrapButton) findViewById(R.id.login_button_id);
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        loginFailureTV.setVisibility(View.INVISIBLE);

        loginBtn.setOnClickListener(view -> {

            final String ipAddress = ipAddressET.getText().toString();
            editor.putString(BASE_URL,ipAddress);
            retrofitServicesInitializer = new RetrofitServicesInitializer(ipAddress);
            service = retrofitServicesInitializer.getUserService();

            final User user = User.builder()
                    .username(usernameOrEmailET.getText().toString())
                    .password(passwordET.getText().toString())
                    .build();

            service.login(user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    editor.putString(USERNAME, response.body().getUsername());
                    editor.putString(PASSWORD, response.body().getPassword());
                    editor.commit();

                    loginFailureTV.setVisibility(View.INVISIBLE);
                    usernameOrEmailET.setText("username");
                    passwordET.setText("password");

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    loginFailureTV.setVisibility(View.VISIBLE);
                }
            });

        });

        registerBtn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);

        });

    }


}