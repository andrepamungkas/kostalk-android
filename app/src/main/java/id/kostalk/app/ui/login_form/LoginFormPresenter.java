package id.kostalk.app.ui.login_form;

import android.text.TextUtils;

import javax.inject.Inject;

import id.kostalk.app.R;
import id.kostalk.app.data.DataManager;
import id.kostalk.app.data.remote.model.LoginRequest;
import id.kostalk.app.data.remote.model.RootResponse;
import id.kostalk.app.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginFormPresenter<V extends LoginFormMvpView> extends BasePresenter<V> implements LoginFormMvpPresenter<V> {

    @Inject
    public LoginFormPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onLoginClick(String kunci) {
        getMvpView().showLoading();

        getDataManager().doLoginApiCall(new LoginRequest(kunci))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootResponse>() {
                    @Override
                    public void accept(RootResponse response) throws Exception {
                        getMvpView().showSnackBar(response.getMessage());
                        if (response.getSuccess()) {
//                            getDataManager().updateUserInfo(
//                                    DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
//                                    response.getAccessToken(),
//                                    response.getPemilik().getId(),
//                                    response.getPemilik().getNoHp());
                            getMvpView().openRequestResetPassword(response.getOtp().getKunci());
                        }
                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().onError(R.string.failed_login);
                    }
                });
    }
}
