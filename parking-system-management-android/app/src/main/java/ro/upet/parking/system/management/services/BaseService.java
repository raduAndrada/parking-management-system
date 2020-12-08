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

public interface BaseService {

    static final String CONTENT_TYPE = "Content-Type: application/json";
    static final String AUTHORIZATION = "X-Authorization: user";
    static final String ID_PATH = "id/{id}";
    static final String CODE_PATH = "code/{code}";
    static final String LIST_PATH = "list";

    @GET(ID_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<Object> getById(@Path("id") final Long id);

    @GET(CODE_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<Object> getByCode(@Path("code") final String code);


    @POST
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<Object> create(@Body final Object body);

    @PUT
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<Object> update(@Body final Object body);

    @DELETE(ID_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<Object> deleteById(@Path ("id") final Long id);

    @DELETE(CODE_PATH)
    @Headers({CONTENT_TYPE, AUTHORIZATION})
    Call<Object> deleteByCode(@Path ("code") final String code);
}
