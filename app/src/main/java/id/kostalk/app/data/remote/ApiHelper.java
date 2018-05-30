package id.kostalk.app.data.remote;

import id.kostalk.app.data.remote.model.BuildingRequest;
import id.kostalk.app.data.remote.model.LoginRequest;
import id.kostalk.app.data.remote.model.MemberRequest;
import id.kostalk.app.data.remote.model.Pemilik;
import id.kostalk.app.data.remote.model.PemilikRequest;
import id.kostalk.app.data.remote.model.RequestResetPasswordRequest;
import id.kostalk.app.data.remote.model.ResetPasswordRequest;
import id.kostalk.app.data.remote.model.RootResponse;
import io.reactivex.Observable;

public interface ApiHelper {

    ApiHeader getApiHeader();

    void setApiHeader(ApiHeader apiHeader);

    Observable<RootResponse> doRegisterApiCall(Pemilik request);

    Observable<RootResponse> doLoginApiCall(LoginRequest request);

    Observable<RootResponse> doRequestResetPasswordApiCall(RequestResetPasswordRequest request);

    Observable<RootResponse> doResetPasswordApiCall(ResetPasswordRequest request);

    /* Building */

    Observable<RootResponse> doGetBuildingApiCall(long ownerId);

    Observable<RootResponse> doAddBuildingApiCall(long ownerId, BuildingRequest.Add request);

    Observable<RootResponse> doUpdateBuildingApiCall(long ownerId, long buildingId, BuildingRequest.Update request);

    Observable<RootResponse> doDeleteBuildingApiCall(long ownerId, long buildingId);

    Observable<RootResponse> doGetOwnerProfileApiCall(long ownerId);

    Observable<RootResponse> doUpdateOwnerProfileApiCall(long idPemilik, PemilikRequest request);

    /* Anggota */

    Observable<RootResponse> doGetMemberApiCall(long idPemilik);

    Observable<RootResponse> doAddMemberApiCall(long buildingId, MemberRequest request);

    Observable<RootResponse> doUpdateMemberApiCall(long idPemilik, long idAnggota, MemberRequest request);

    Observable<RootResponse> doDeleteMemberApiCall(long buildingId, long memberId);

    Observable<RootResponse> doGetHistoryApiCall(long buildingId);
}
