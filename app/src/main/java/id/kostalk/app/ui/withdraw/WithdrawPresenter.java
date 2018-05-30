package id.kostalk.app.ui.withdraw;

import javax.inject.Inject;

import id.kostalk.app.data.DataManager;
import id.kostalk.app.ui.base.BasePresenter;

public class WithdrawPresenter<V extends WithdrawMvpView> extends BasePresenter<V> implements WithdrawMvpPresenter<V> {

    @Inject
    public WithdrawPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
