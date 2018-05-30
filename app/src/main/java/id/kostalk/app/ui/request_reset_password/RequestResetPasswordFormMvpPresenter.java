package id.kostalk.app.ui.request_reset_password;

import id.kostalk.app.di.PerActivity;
import id.kostalk.app.ui.base.MvpPresenter;

@PerActivity
public interface RequestResetPasswordFormMvpPresenter<V extends RequestResetPasswordFormMvpView> extends MvpPresenter<V> {

    void onRequestResetPasswordClick(String phone,String kode);
}
