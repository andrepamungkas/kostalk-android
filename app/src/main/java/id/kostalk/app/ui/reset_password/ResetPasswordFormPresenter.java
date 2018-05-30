package id.kostalk.app.ui.reset_password;

import android.text.TextUtils;

import javax.inject.Inject;

import id.kostalk.app.R;
import id.kostalk.app.data.DataManager;
import id.kostalk.app.data.remote.model.ResetPasswordRequest;
import id.kostalk.app.data.remote.model.RootResponse;
import id.kostalk.app.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ResetPasswordFormPresenter<V extends ResetPasswordFormMvpView> extends BasePresenter<V> implements ResetPasswordFormMvpPresenter<V> {

    @Inject
    public ResetPasswordFormPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onResetPasswordClick(String phone, String code, String newPassword) {
        if (TextUtils.isEmpty(phone)) {
            getMvpView().onError(R.string.empty_phone);
            return;
        }
        if (TextUtils.isEmpty(code)) {
            getMvpView().onError(R.string.empty_code);
            return;
        }
        if (TextUtils.isEmpty(newPassword)) {
            getMvpView().onError(R.string.empty_password);
            return;
        }
        getMvpView().showLoading();

        getDataManager().doResetPasswordApiCall(new ResetPasswordRequest(phone, code, newPassword))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootResponse>() {
                    @Override
                    public void accept(RootResponse response) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().showResetPasswordResult(response);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().onError(R.string.some_error);
                    }
                });
    }
}
