package ro.upet.parking.system.management.services;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BaseService {

    String CONTENT_TYPE = "Content-Type: application/json";
    String AUTHORIZATION = "X-Authorization: user";

    String ID_PATH = "id/{id}";
    String CODE_PATH = "code/{code}";
    String LIST_PATH = "list";
    String USER_USERNAME_PATH = "user/{username}";

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
