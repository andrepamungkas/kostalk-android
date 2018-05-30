package id.kostalk.app.ui.register_form;

import id.kostalk.app.data.remote.model.RootResponse;
import id.kostalk.app.ui.base.MvpView;

public interface RegisterFormMvpView extends MvpView {

    void showRegisterResult(RootResponse response);
}
