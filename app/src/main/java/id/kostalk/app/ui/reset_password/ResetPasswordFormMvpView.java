package id.kostalk.app.ui.reset_password;

import id.kostalk.app.data.remote.model.RootResponse;
import id.kostalk.app.ui.base.MvpView;

public interface ResetPasswordFormMvpView extends MvpView {

    void showResetPasswordResult(RootResponse response);
}
