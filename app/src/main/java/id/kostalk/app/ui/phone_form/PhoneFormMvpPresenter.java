package id.kostalk.app.ui.phone_form;

import id.kostalk.app.di.PerActivity;
import id.kostalk.app.ui.base.MvpPresenter;

@PerActivity
public interface PhoneFormMvpPresenter<V extends PhoneFormMvpView> extends MvpPresenter<V> {

    void onSaveClick(String nama, String email, String noHp);
}
