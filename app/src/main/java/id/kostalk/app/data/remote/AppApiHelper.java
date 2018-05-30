package id.kostalk.app.data.remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.kostalk.app.data.remote.model.BuildingRequest;
import id.kostalk.app.data.remote.model.LoginRequest;
import id.kostalk.app.data.remote.model.MemberRequest;
import id.kostalk.app.data.remote.model.Pemilik;
import id.kostalk.app.data.remote.model.PemilikRequest;
import id.kostalk.app.data.remote.model.RequestResetPasswordRequest;
import id.kostalk.app.data.remote.model.ResetPasswordRequest;
import id.kostalk.app.data.remote.model.RootResponse;
import io.reactivex.Observable;

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;
    private ApiCall mApiCall;

    @Inject
    public AppApiHelper(ApiHeader apiHeader, ApiCall apiCall) {
        mApiHeader = apiHeader;
        mApiCall = apiCall;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public void setApiHeader(ApiHeader apiHeader) {
        if (apiHeader != null) {
            mApiHeader.setApiKey(apiHeader.getApiKey());
            mApiHeader.setUserId(apiHeader.getUserId());
            mApiHeader.setAccessToken(apiHeader.getAccessToken());
        }
    }

    @Override
    public Observable<RootResponse> doRegisterApiCall(Pemilik request) {
        return mApiCall.doRegister(request);
    }

    @Override
    public Observable<RootResponse> doLoginApiCall(LoginRequest request) {
        return mApiCall.doLogin(request);
    }

    @Override
    public Observable<RootResponse> doRequestResetPasswordApiCall(RequestResetPasswordRequest request) {
        return mApiCall.doRequestResetPassword(request);
    }

    @Override
    public Observable<RootResponse> doResetPasswordApiCall(ResetPasswordRequest request) {
        return mApiCall.doResetPassword(request);
    }

    /* Building */

    @Override
    public Observable<RootResponse> doGetBuildingApiCall(long ownerId) {
        return mApiCall.doGetBuilding(ownerId);
    }

    @Override
    public Observable<RootResponse> doAddBuildingApiCall(long ownerId, BuildingRequest.Add request) {
        return mApiCall.doAddBuilding(ownerId, request);
    }

    @Override
    public Observable<RootResponse> doUpdateBuildingApiCall(long ownerId, long buildingId, BuildingRequest.Update request) {
        return mApiCall.doUpdateBuilding(ownerId, buildingId, request);
    }

    @Override
    public Observable<RootResponse> doDeleteBuildingApiCall(long ownerId, long buildingId) {
        return mApiCall.doDeleteBuilding(ownerId, buildingId);
    }

    @Override
    public Observable<RootResponse> doGetOwnerProfileApiCall(long ownerId) {
        return mApiCall.doGetOwnerProfile(ownerId);
    }

    @Override
    public Observable<RootResponse> doUpdateOwnerProfileApiCall(long idPemilik, PemilikRequest request) {
        return mApiCall.doUpdateOwnerProfile(idPemilik, request);
    }

    /* Anggota */

    @Override
    public Observable<RootResponse> doGetMemberApiCall(long idPemilik) {
        return mApiCall.doGetMember(idPemilik);
    }

    @Override
    public Observable<RootResponse> doAddMemberApiCall(long buildingId, MemberRequest request) {
        return mApiCall.doAddMember(buildingId, request);
    }

    @Override
    public Observable<RootResponse> doUpdateMemberApiCall(long idPemilik, long idAnggota, MemberRequest request) {
        return mApiCall.doUpdateMember(idPemilik, idAnggota, request);
    }

    @Override
    public Observable<RootResponse> doDeleteMemberApiCall(long buildingId, long memberId) {
        return mApiCall.doDeleteMember(buildingId, memberId);
    }

    @Override
    public Observable<RootResponse> doGetHistoryApiCall(long buildingId) {
        return mApiCall.doGetHistory(buildingId);
    }
}
