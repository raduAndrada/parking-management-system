package ro.upet.parking.system.management.adaptors;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.upet.parking.system.management.model.Membership;
import ro.upet.parking.system.management.services.MembershipService;

import static ro.upet.parking.system.management.activities.common.StringConstants.SERVER_BASE_ADDRESS;

public class MembershipAdapter extends BaseAdaptor<Membership>{

    private static final String MEMBERSHIPS_URL = SERVER_BASE_ADDRESS + "v1/memberships";

    public MembershipAdapter(@NonNull Context context, int resource, @NonNull List<Membership> items) {
        super(context, resource, items);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }




}
