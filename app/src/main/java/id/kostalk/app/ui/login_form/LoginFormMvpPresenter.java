package id.kostalk.app.ui.login_form;

import id.kostalk.app.di.PerActivity;
import id.kostalk.app.ui.base.MvpPresenter;

@PerActivity
public interface LoginFormMvpPresenter<V extends LoginFormMvpView> extends MvpPresenter<V> {

    void onLoginClick(String kunci);
}
