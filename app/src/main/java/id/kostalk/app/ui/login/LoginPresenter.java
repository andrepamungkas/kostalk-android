package id.kostalk.app.ui.login;

import javax.inject.Inject;

import id.kostalk.app.data.DataManager;
import id.kostalk.app.ui.base.BasePresenter;

/**
 * Created by andre on 3/24/2018.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    @Inject
    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        getMvpView().showLoginFormFragment();
    }
}
