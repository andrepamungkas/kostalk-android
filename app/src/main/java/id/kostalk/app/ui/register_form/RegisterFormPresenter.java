package id.kostalk.app.ui.register_form;

import android.text.TextUtils;

import javax.inject.Inject;

import id.kostalk.app.R;
import id.kostalk.app.data.DataManager;
import id.kostalk.app.data.remote.model.Pemilik;
import id.kostalk.app.data.remote.model.RootResponse;
import id.kostalk.app.ui.base.BasePresenter;
import id.kostalk.app.utils.CommonUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterFormPresenter<V extends RegisterFormMvpView> extends BasePresenter<V> implements RegisterFormMvpPresenter<V> {

    @Inject
    public RegisterFormPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onRegisterClick(String name, String phone, String email) {
        if (TextUtils.isEmpty(name)) {
            getMvpView().onError(R.string.empty_name);
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            getMvpView().onError(R.string.empty_phone);
            return;
        }
        if (TextUtils.isEmpty(email)) {
            getMvpView().onError(R.string.empty_email);
            return;
        }
        if (!CommonUtils.isEmailValid(email)) {
            getMvpView().onError(R.string.invalid_email);
            return;
        }
        getMvpView().showLoading();

        getDataManager().doRegisterApiCall(new Pemilik(name, phone, email))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootResponse>() {
                    @Override
                    public void accept(RootResponse response) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().showRegisterResult(response);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().hideLoading();
                        handleApiError(throwable);
                    }
                });
    }
}
