package ro.upet.parking.system.management.adaptors;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;

import ro.upet.parking.system.management.R;
import ro.upet.parking.system.management.model.Reservation;


public class ReservationAdapter extends BaseAdaptor<Reservation> {


    public ReservationAdapter(@NonNull Context context, @NonNull List<Reservation> items) {
        super(context, 0, items);
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        this.username = sharedPref.getString(USERNAME, "none");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Reservation reservation = (Reservation) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.reservation_item, parent, false);
        }

        final TextView costTextView = (TextView) convertView.findViewById(R.id.reservation_item_cost_id);
        costTextView.setText(reservation.getCost());

        final TextView parkingNameTextView = (TextView) convertView.findViewById(R.id.reservation_item_parking_name_id);
        parkingNameTextView.setText(reservation.getParkingSpot().getParkingName());

        final TextView parkingLevelNumberTextView = (TextView) convertView.findViewById(R.id.reservation_item_parking_level_number_id);
        parkingLevelNumberTextView.setText(reservation.getParkingSpot().getParkingLevelNumber());

        final TextView parkingZoneLetterTextView = (TextView) convertView.findViewById(R.id.reservation_item_parking_zone_letter_id);
        parkingZoneLetterTextView.setText(reservation.getParkingSpot().getParkingZoneLetter());

        final TextView parkingSpotNumberTextView = (TextView) convertView.findViewById(R.id.reservation_item_parking_spot_number_id);
        parkingSpotNumberTextView.setText(reservation.getParkingSpot().getNumber());

        final TextView startTimeTextView = (TextView) convertView.findViewById(R.id.reservation_item_start_time_id);
        startTimeTextView.setText(reservation.getStartTime().toString());

        final TextView endTimeTextView = (TextView) convertView.findViewById(R.id.reservation_item_end_time_id);
        endTimeTextView.setText(reservation.getEndTime().toString());

        return convertView;
    }


}
