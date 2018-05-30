package id.kostalk.app.data.remote;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import id.kostalk.app.BuildConfig;
import id.kostalk.app.data.remote.model.BuildingRequest;
import id.kostalk.app.data.remote.model.LoginRequest;
import id.kostalk.app.data.remote.model.MemberRequest;
import id.kostalk.app.data.remote.model.Pemilik;
import id.kostalk.app.data.remote.model.PemilikRequest;
import id.kostalk.app.data.remote.model.RequestResetPasswordRequest;
import id.kostalk.app.data.remote.model.ResetPasswordRequest;
import id.kostalk.app.data.remote.model.RootResponse;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiCall {

    String HEADER_PARAM_SEPARATOR = ":";

    String ENDPOINT_REGISTER = "pemilik/daftar";
    String ENDPOINT_OTP = "pemilik/auth/otp";
    String ENDPOINT_VerifikasiOtp = "pemilik/auth/verifikasi";
    String ENDPOINT_RESET_PASSWORD = "owners/resetPassword";
    String ENDPOINT_OWNER_BY_ID = "pemilik/{idPemilik}";
    String ENDPOINT_BUILDING = "owners/{ownerId}/buildings";
    String ENDPOINT_BUILDING_BY_ID = "owners/{ownerId}/buildings/{buildingId}";
    String ENDPOINT_MEMBER = "pemilik/{idPemilik}/anggota";
    String ENDPOINT_MEMBER_BY_ID = "pemilik/{idPemilik}/anggota/{idAnggota}";
    String ENDPOINT_TRANSACTION_HISTORY = "buildings/{buildingId}/transaction_history";

    @POST(ENDPOINT_REGISTER)
    @Headers(ApiHeader.API_AUTH_TYPE + HEADER_PARAM_SEPARATOR + ApiHeader.PUBLIC_API)
    Observable<RootResponse> doRegister(@Body Pemilik request);

    @POST(ENDPOINT_OTP)
    @Headers(ApiHeader.API_AUTH_TYPE + HEADER_PARAM_SEPARATOR + ApiHeader.PUBLIC_API)
    Observable<RootResponse> doLogin(@Body LoginRequest request);

    @POST(ENDPOINT_VerifikasiOtp)
    @Headers(ApiHeader.API_AUTH_TYPE + HEADER_PARAM_SEPARATOR + ApiHeader.PUBLIC_API)
    Observable<RootResponse> doRequestResetPassword(@Body RequestResetPasswordRequest request);

    @POST(ENDPOINT_RESET_PASSWORD)
    @Headers(ApiHeader.API_AUTH_TYPE + HEADER_PARAM_SEPARATOR + ApiHeader.PUBLIC_API)
    Observable<RootResponse> doResetPassword(@Body ResetPasswordRequest request);

    /* Building */

    @GET(ENDPOINT_BUILDING)
    @Headers(ApiHeader.API_AUTH_TYPE + HEADER_PARAM_SEPARATOR + ApiHeader.PROTECTED_API)
    Observable<RootResponse> doGetBuilding(@Path("ownerId") long ownerId);

    @POST(ENDPOINT_BUILDING)
    @Headers(ApiHeader.API_AUTH_TYPE + HEADER_PARAM_SEPARATOR + ApiHeader.PROTECTED_API)
    Observable<RootResponse> doAddBuilding(@Path("ownerId") long ownerId, @Body BuildingRequest.Add request);

    @PUT(ENDPOINT_BUILDING_BY_ID)
    @Headers(ApiHeader.API_AUTH_TYPE + HEADER_PARAM_SEPARATOR + ApiHeader.PROTECTED_API)
    Observable<RootResponse> doUpdateBuilding(@Path("ownerId") long ownerId, @Path("buildingId") long buildingId, @Body BuildingRequest.Update request);

    @DELETE(ENDPOINT_BUILDING_BY_ID)
    @Headers(ApiHeader.API_AUTH_TYPE + HEADER_PARAM_SEPARATOR + ApiHeader.PROTECTED_API)
    Observable<RootResponse> doDeleteBuilding(@Path("ownerId") long ownerId, @Path("buildingId") long buildingId);

    /*Pemilik*/

    @GET(ENDPOINT_OWNER_BY_ID)
    @Headers(ApiHeader.API_AUTH_TYPE + HEADER_PARAM_SEPARATOR + ApiHeader.PROTECTED_API)
    Observable<RootResponse> doGetOwnerProfile(@Path("idPemilik") long ownerId);

    @PUT(ENDPOINT_OWNER_BY_ID)
    @Headers(ApiHeader.API_AUTH_TYPE + HEADER_PARAM_SEPARATOR + ApiHeader.PROTECTED_API)
    Observable<RootResponse> doUpdateOwnerProfile(@Path("idPemilik") long ownerId, @Body PemilikRequest request);

    /* Anggota */

    @GET(ENDPOINT_MEMBER)
    @Headers(ApiHeader.API_AUTH_TYPE + HEADER_PARAM_SEPARATOR + ApiHeader.PROTECTED_API)
    Observable<RootResponse> doGetMember(@Path("idPemilik") long idPemilik);

    @POST(ENDPOINT_MEMBER)
    @Headers(ApiHeader.API_AUTH_TYPE + HEADER_PARAM_SEPARATOR + ApiHeader.PROTECTED_API)
    Observable<RootResponse> doAddMember(@Path("idPemilik") long idPemilik, @Body MemberRequest request);

    @PUT(ENDPOINT_MEMBER_BY_ID)
    @Headers(ApiHeader.API_AUTH_TYPE + HEADER_PARAM_SEPARATOR + ApiHeader.PROTECTED_API)
    Observable<RootResponse> doUpdateMember(@Path("idPemilik") long idPemilik, @Path("idAnggota") long idAnggota, @Body MemberRequest request);

    @DELETE(ENDPOINT_MEMBER_BY_ID)
    @Headers(ApiHeader.API_AUTH_TYPE + HEADER_PARAM_SEPARATOR + ApiHeader.PROTECTED_API)
    Observable<RootResponse> doDeleteMember(@Path("idPemilik") long idPemilik, @Path("idAnggota") long idAnggota);

    @GET(ENDPOINT_TRANSACTION_HISTORY)
    @Headers(ApiHeader.API_AUTH_TYPE + HEADER_PARAM_SEPARATOR + ApiHeader.PROTECTED_API)
    Observable<RootResponse> doGetHistory(@Path("buildingId") long buildingId);

    class Factory {

        private static final int NETWORK_CALL_TIMEOUT = 60;

        public static ApiCall create(ApiInterceptor apiInterceptor) {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(apiInterceptor);

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(
                    BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

            builder.addInterceptor(logging);
            builder.readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS);
            builder.writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS);

            OkHttpClient httpClient = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(ApiCall.class);
        }
    }
}
