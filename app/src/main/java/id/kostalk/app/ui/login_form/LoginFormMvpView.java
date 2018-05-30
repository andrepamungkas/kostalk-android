package id.kostalk.app.ui.login_form;

import id.kostalk.app.ui.base.MvpView;

public interface LoginFormMvpView extends MvpView {

    void openMainActivity();

    void openRequestResetPassword(String kunci);
}
