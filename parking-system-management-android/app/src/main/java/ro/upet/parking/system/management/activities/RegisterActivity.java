package ro.upet.parking.system.management.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.upet.parking.system.management.R;
import ro.upet.parking.system.management.model.ImtUser;
import ro.upet.parking.system.management.model.ImtUserCreate;
import ro.upet.parking.system.management.model.ImtVehicle;
import ro.upet.parking.system.management.model.MdfUserCreate;
import ro.upet.parking.system.management.model.Size;
import ro.upet.parking.system.management.model.User;
import ro.upet.parking.system.management.model.UserCreate;
import ro.upet.parking.system.management.model.UserType;
import ro.upet.parking.system.management.services.UserService;

import static ro.upet.parking.system.management.activities.common.StringConstants.PASSWORD;
import static ro.upet.parking.system.management.activities.common.StringConstants.SHARED_PREFERENCES;
import static ro.upet.parking.system.management.activities.common.StringConstants.USERNAME;
import static ro.upet.parking.system.management.activities.common.StringConstants.USERS_URL;

public class RegisterActivity extends AppCompatActivity {

    private  static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(USERS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static final UserService service = retrofit.create(UserService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TypefaceProvider.registerDefaultIconSets();

        final MdfUserCreate userCreate = MdfUserCreate.create();
        final EditText usernameET = (EditText) findViewById(R.id.register_user_username_id);
        final EditText passwordET = (EditText) findViewById(R.id.register_user_password_id);
        final EditText emailET = (EditText) findViewById(R.id.register_user_email_id);
        final EditText nameET = (EditText) findViewById(R.id.register_user_name_id);
        final EditText birthdayET = (EditText) findViewById(R.id.register_user_birthday_id);
        final EditText licencePlateET = (EditText) findViewById(R.id.register_user_vehicle_licence_plate_id);
        final EditText creditCardNumberET = (EditText) findViewById(R.id.register_credit_card_number_id);
        final EditText creditCardCcvET = (EditText) findViewById(R.id.register_credit_card_ccv_id);

        final BootstrapButton registerBtn =(BootstrapButton) findViewById(R.id.register_user_create_btn_id);

        final Spinner creditCardExpMonthSpinner = (Spinner) findViewById(R.id.register_credit_card_exp_month_id);
        final ArrayAdapter<CharSequence> monthsAdapter = ArrayAdapter.createFromResource(this, R.array.months_array, android.R.layout.simple_spinner_item);
        monthsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        creditCardExpMonthSpinner.setAdapter(monthsAdapter);
        creditCardExpMonthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                userCreate.setCreditCardExpMonth((String) adapterView.getItemAtPosition(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //TODO add error on view
            }
        });

        final Spinner creditCardExpYearSpinner = (Spinner) findViewById(R.id.register_credit_card_exp_year_id);
        final ArrayAdapter<CharSequence> yearsAdapter = ArrayAdapter.createFromResource(this, R.array.years_array, android.R.layout.simple_spinner_item);
        yearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        creditCardExpYearSpinner.setAdapter(yearsAdapter);
        creditCardExpYearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                userCreate.setCreditCardExpYear((String) adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //TODO add error on view
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userCreate.setVehicle(ImtVehicle.builder()
                                    .licencePlate(licencePlateET.getText().toString())
                                    .size(Size.MEDIUM)
                                    .user(ImtUser.builder()
                                            .username(usernameET.getText().toString())
                                            .email(emailET.getText().toString())
                                            .name(nameET.getText().toString())
                                            .birthday(birthdayET.getText().toString())
                                            .password(passwordET.getText().toString())
                                            .userType(UserType.CUSTOMER)
                                            .build())
                                    .build())
                        .setCreditCardNumber(creditCardNumberET.getText().toString())
                        .setCreditCardCCV(creditCardCcvET.getText().toString());

                service.createCustomer(ImtUserCreate.builder().from(userCreate).build()).enqueue(new Callback<ImtUser>() {
                    @Override
                    public void onResponse(Call<ImtUser> call, Response<ImtUser> response) {
                        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString(USERNAME, usernameET.getText().toString());
                        editor.putString(PASSWORD, passwordET.getText().toString());
                        editor.commit();

                        Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ImtUser> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });





    }
}