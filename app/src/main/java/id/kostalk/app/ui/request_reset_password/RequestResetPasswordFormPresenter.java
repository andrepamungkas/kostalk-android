package id.kostalk.app.ui.request_reset_password;

import android.text.TextUtils;

import javax.inject.Inject;

import id.kostalk.app.R;
import id.kostalk.app.data.DataManager;
import id.kostalk.app.data.remote.model.RequestResetPasswordRequest;
import id.kostalk.app.data.remote.model.RootResponse;
import id.kostalk.app.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RequestResetPasswordFormPresenter<V extends RequestResetPasswordFormMvpView> extends BasePresenter<V> implements RequestResetPasswordFormMvpPresenter<V> {

    @Inject
    public RequestResetPasswordFormPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onRequestResetPasswordClick(String phone,String kode) {
        if (TextUtils.isEmpty(phone)) {
            getMvpView().onError(R.string.empty_phone);
            return;
        }
        getMvpView().showLoading();

        getDataManager().doRequestResetPasswordApiCall(new RequestResetPasswordRequest(phone, kode))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootResponse>() {
                    @Override
                    public void accept(RootResponse response) throws Exception {
                        getMvpView().hideLoading();
                        if (response.getSuccess()) {
                            getDataManager().updateUserInfo(
                                    DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                    response.getAccessToken(),
                                    response.getPemilik().getId(),
                                    response.getPemilik().getNoHp());
                            getMvpView().openMainActivity();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().onError(R.string.failed_request_reset_password);
                    }
                });
    }
}
