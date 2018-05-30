package id.kostalk.app.data;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.kostalk.app.data.local.prefs.PreferencesHelper;
import id.kostalk.app.data.remote.ApiHeader;
import id.kostalk.app.data.remote.ApiHelper;
import id.kostalk.app.data.remote.model.BuildingRequest;
import id.kostalk.app.data.remote.model.LoginRequest;
import id.kostalk.app.data.remote.model.MemberRequest;
import id.kostalk.app.data.remote.model.Pemilik;
import id.kostalk.app.data.remote.model.PemilikRequest;
import id.kostalk.app.data.remote.model.RequestResetPasswordRequest;
import id.kostalk.app.data.remote.model.ResetPasswordRequest;
import id.kostalk.app.data.remote.model.RootResponse;
import id.kostalk.app.di.ApplicationContext;
import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final ApiHelper mApiHelper;
    private final PreferencesHelper mPreferencesHelper;
//    private final DbHelper mDbHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          ApiHelper apiHelper,
                          PreferencesHelper preferencesHelper
                          /*DbHelper dbHelper*/) {
        mContext = context;
        mApiHelper = apiHelper;
        mPreferencesHelper = preferencesHelper;
//        mDbHelper = dbHelper;
    }

    @Override
    public void updateApiHeader(String accessToken, Long userId) {
        mApiHelper.getApiHeader().setAccessToken(accessToken);
        mApiHelper.getApiHeader().setUserId(userId);
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                null,
                null,
                null);
    }

    @Override
    public void updateUserInfo(
            LoggedInMode loggedInMode,
            String accessToken,
            Long userId,
            String userName) {

        setCurrentUserLoggedInMode(loggedInMode);
        setAccessToken(accessToken);
        setCurrentUserId(userId);

        updateApiHeader(accessToken, userId);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public Long getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Long userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public String getCurrentUserEmail() {
        return mPreferencesHelper.getCurrentUserEmail();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPreferencesHelper.setCurrentUserEmail(email);
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
        mApiHelper.getApiHeader().setAccessToken(accessToken);
    }

    @Override
    public ApiHeader getApiHeader() {
        return null;
    }

    @Override
    public void setApiHeader(ApiHeader apiHeader) {

    }

    @Override
    public Observable<RootResponse> doRegisterApiCall(Pemilik request) {
        return mApiHelper.doRegisterApiCall(request);
    }

    @Override
    public Observable<RootResponse> doLoginApiCall(LoginRequest request) {
        return mApiHelper.doLoginApiCall(request);
    }

    @Override
    public Observable<RootResponse> doRequestResetPasswordApiCall(RequestResetPasswordRequest request) {
        return mApiHelper.doRequestResetPasswordApiCall(request);
    }

    @Override
    public Observable<RootResponse> doResetPasswordApiCall(ResetPasswordRequest request) {
        return mApiHelper.doResetPasswordApiCall(request);
    }

    /* Building */

    @Override
    public Observable<RootResponse> doGetBuildingApiCall(long ownerId) {
        return mApiHelper.doGetBuildingApiCall(ownerId);
    }

    @Override
    public Observable<RootResponse> doAddBuildingApiCall(long ownerId, BuildingRequest.Add request) {
        return mApiHelper.doAddBuildingApiCall(ownerId, request);
    }

    @Override
    public Observable<RootResponse> doUpdateBuildingApiCall(long ownerId, long buildingId, BuildingRequest.Update request) {
        return mApiHelper.doUpdateBuildingApiCall(ownerId, buildingId, request);
    }

    @Override
    public Observable<RootResponse> doDeleteBuildingApiCall(long ownerId, long buildingId) {
        return mApiHelper.doDeleteBuildingApiCall(ownerId, buildingId);
    }

    @Override
    public Observable<RootResponse> doGetOwnerProfileApiCall(long ownerId) {
        return mApiHelper.doGetOwnerProfileApiCall(ownerId);
    }

    @Override
    public Observable<RootResponse> doUpdateOwnerProfileApiCall(long idPemilik, PemilikRequest request) {
        return mApiHelper.doUpdateOwnerProfileApiCall(idPemilik, request);
    }

    /* Anggota */

    @Override
    public Observable<RootResponse> doGetMemberApiCall(long idPemilik) {
        return mApiHelper.doGetMemberApiCall(idPemilik);
    }

    @Override
    public Observable<RootResponse> doAddMemberApiCall(long buildingId, MemberRequest request) {
        return mApiHelper.doAddMemberApiCall(buildingId, request);
    }

    @Override
    public Observable<RootResponse> doUpdateMemberApiCall(long idPemilik, long idAnggota, MemberRequest request) {
        return mApiHelper.doUpdateMemberApiCall(idPemilik, idAnggota, request);
    }

    @Override
    public Observable<RootResponse> doDeleteMemberApiCall(long buildingId, long memberId) {
        return mApiHelper.doDeleteMemberApiCall(buildingId, memberId);
    }

    @Override
    public Observable<RootResponse> doGetHistoryApiCall(long buildingId) {
        return mApiHelper.doGetHistoryApiCall(buildingId);
    }
}
