package ro.upet.parking.system.management.activities.common;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import ro.upet.parking.system.management.R;
import ro.upet.parking.system.management.activities.MainActivity;
import ro.upet.parking.system.management.activities.ReservationActivity;
import ro.upet.parking.system.management.activities.ReservationHistoryActivity;
import ro.upet.parking.system.management.activities.UserProfileActivity;

import static ro.upet.parking.system.management.activities.common.StringConstants.SHARED_PREFERENCES;
import static ro.upet.parking.system.management.activities.common.StringConstants.USERNAME;

public class MenuHelper extends AppCompatActivity {




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.menu_home_id) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.menu_my_reservations_id) {
            Intent intent = new Intent(this, ReservationHistoryActivity.class);
            startActivity(intent);
        }
        if (id == R.id.menu_new_reservation_id) {
            Intent intent = new Intent(this, ReservationActivity.class);
            startActivity(intent);
        }
        if (id == R.id.menu_my_profile_id) {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
