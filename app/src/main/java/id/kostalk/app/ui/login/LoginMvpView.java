package id.kostalk.app.ui.login;

import id.kostalk.app.ui.base.MvpView;

public interface LoginMvpView extends MvpView {

    void showLoginFormFragment();

    void showRegisterFormFragment();

    void showRequestResetPasswordFormFragment(String kunci);

    void showResetPasswordFormFragment(String phone);
}
