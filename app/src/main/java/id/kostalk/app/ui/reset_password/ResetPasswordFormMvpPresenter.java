package id.kostalk.app.ui.reset_password;

import id.kostalk.app.di.PerActivity;
import id.kostalk.app.ui.base.MvpPresenter;

@PerActivity
public interface ResetPasswordFormMvpPresenter<V extends ResetPasswordFormMvpView> extends MvpPresenter<V> {

    void onResetPasswordClick(String phone, String code, String newPassword);
}
