package id.kostalk.app.ui.account;

import id.kostalk.app.di.PerActivity;
import id.kostalk.app.ui.base.MvpPresenter;

@PerActivity
public interface AccountMvpPresenter<V extends AccountMvpView> extends MvpPresenter<V> {

    void onGetPemilik();
}
