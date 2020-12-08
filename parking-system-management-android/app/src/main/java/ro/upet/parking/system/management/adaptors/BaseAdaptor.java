package ro.upet.parking.system.management.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import retrofit2.Retrofit;
import ro.upet.parking.system.management.services.BaseService;

public class BaseAdaptor<T> extends ArrayAdapter {



    protected String username;

    public BaseAdaptor(@NonNull Context context, int resource, @NonNull List<T> items) {
        super(context, resource, items);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
