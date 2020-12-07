package ro.upet.parking.system.management.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BaseService<T> {

    static final String CONTENT_TYPE = "Content-Type: application/json";
    static final String AUTHORIZATION = "X-Authorization: user";
    static final String ID_PATH = "/id/{id}";
    static final String CODE_PATH = "/code/{code}";

    @GET(ID_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<T> getById(@Path("id") final Long id);

    @GET(CODE_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<T> getByCode(@Path("code") final String code);

    @GET
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<List<T>> getAll();

    @POST
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<T> create(@Body final T body);

    @PUT
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<T> update(@Body final T body);

    @DELETE(ID_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<T> deleteById(@Path ("id") final Long id);

    @DELETE(CODE_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<T> deleteByCode(@Path ("code") final String code);
}
