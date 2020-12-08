package ro.upet.parking.system.management.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import ro.upet.parking.system.management.R;
import ro.upet.parking.system.management.activities.ReservationActivity;
import ro.upet.parking.system.management.activities.ReservationHistoryActivity;
import ro.upet.parking.system.management.activities.UserProfileActivity;
import ro.upet.parking.system.management.activities.common.MenuHelper;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends MenuHelper {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

}