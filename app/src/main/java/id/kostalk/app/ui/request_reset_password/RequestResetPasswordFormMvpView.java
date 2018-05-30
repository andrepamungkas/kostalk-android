package id.kostalk.app.ui.request_reset_password;

import id.kostalk.app.data.remote.model.RootResponse;
import id.kostalk.app.ui.base.MvpView;

public interface RequestResetPasswordFormMvpView extends MvpView {

    void openMainActivity();

    void showRequestResetPasswordResult(RootResponse response);
}
