package ro.upet.parking.system.management.activities.common;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.common.base.Splitter;

import java.util.Objects;

import ro.upet.parking.system.management.R;
import ro.upet.parking.system.management.model.MdfUserCreate;

public class BaseUtils {

    public static String convertDateToParsingInstantFormat(final int year, final int month, final int day) {
        return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day) + "T";
    }

    public static String convertTimeToParsingInstantFormat(final String date, final String time){
        return date + time + ":00.00Z";
    }

    public static String[] extractDateFromInstantFormat(final String instant) {
        final int splitIndex = instant.indexOf('T');
        final String date = instant.substring(0, splitIndex);
        final String time = instant.substring(splitIndex + 1, instant.length() - 7);
        return new String[]{date, time};
    }

    public static String displayDateTime(final String [] dateTime){
        StringBuilder sb = new StringBuilder();
            sb
                .append(dateTime[0])
                .append(" at ")
                .append(dateTime[1]);
        return sb.toString();
    }

    public static String handleNulls(final String string, final String alternative){
        return Objects.nonNull(string) && !string.isEmpty() ? string : alternative;
    }

    public static void initCreditCardSpinners(Context context, final MdfUserCreate userCreate, final Spinner spinner, final int arrayId)
    {
        final ArrayAdapter<CharSequence> monthsAdapter = ArrayAdapter.createFromResource(context, arrayId , android.R.layout.simple_spinner_item);
        monthsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(monthsAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (arrayId == R.array.months_array) {
                    userCreate.setCreditCardExpMonth((String) adapterView.getItemAtPosition(i));
                } else if (arrayId == R.array.years_array){
                    userCreate.setCreditCardExpYear((String) adapterView.getItemAtPosition(i));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //TODO add error on view
            }
        });

    }
}
