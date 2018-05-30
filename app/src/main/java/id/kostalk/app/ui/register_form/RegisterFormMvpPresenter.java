package id.kostalk.app.ui.register_form;

import id.kostalk.app.di.PerActivity;
import id.kostalk.app.ui.base.MvpPresenter;

@PerActivity
public interface RegisterFormMvpPresenter<V extends RegisterFormMvpView> extends MvpPresenter<V> {

    void onRegisterClick(String name, String phone, String email);
}
