package ro.upet.parking.system.management.activities.common;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.common.base.Splitter;

import java.util.Objects;

import lombok.experimental.UtilityClass;
import ro.upet.parking.system.management.R;
import ro.upet.parking.system.management.model.UserCreate;

/**
 * @author Andrada
 * Utility class for Strings passed to the Android client
 */
@UtilityClass
public class BaseUtils {

    private static final String HH_MM_REGEX = "^(\\d{2}:\\d{2})$";
    /**
     *
     * @param year of the instant
     * @param month of the instant
     * @param day of the instant
     * @return date part of instant parsable string
     */
    public static String convertDateToParsingInstantFormat(final int year, final int month, final int day) {
        return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day) + "T";
    }

    /**
     * Convert yy mm dd to instant parsing format yyyy-mm-ddThh:mm:ss.msmsZ
     * @param date date for the instant
     * @param time time of it
     * @return a instant parsable string
     */
    public static String convertTimeToParsingInstantFormat(final String date, final String time){
        return date + time + ":00.00Z";
    }

    /**
     * Get the date and time from instant string
     * @param instant the string with the instant details
     * @return the array containing at [0] the date and at [1] the time
     */
    public static String[] extractDateFromInstantFormat(final String instant) {
        final int splitIndex = instant.indexOf('T');
        final String date = instant.substring(0, splitIndex);
        final String time = instant.substring(splitIndex + 1, instant.length() - 7);
        return new String[]{date, time};
    }

    /**
     * @param dateTime array with date and time
     * @return a formatted string
     */
    public static String displayDateTime(final String [] dateTime){
        StringBuilder sb = new StringBuilder();
            sb
                .append(dateTime[0])
                .append(" at ")
                .append(dateTime[1]);
        return sb.toString();
    }

    /**
     * @param string to check if null or empty
     * @param alternative the message to be displayed
     * @return the string with the null values handled
     */
    public static String handleNulls(final String string, final String alternative){
        return Objects.nonNull(string) && !string.isEmpty() ? string : alternative;
    }

    /**
     * @param context
     * @param userCreate user to be created
     * @param spinner the element containing the details about the user's credit card
     * @param arrayId the array used as a spinner
     */
    public static void initCreditCardSpinners(Context context, final UserCreate userCreate, final Spinner spinner, final int arrayId)
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
            }
        });

    }

    public static boolean isValidHourAndMinute(final String time){
        return time.matches(HH_MM_REGEX);
    }
}
