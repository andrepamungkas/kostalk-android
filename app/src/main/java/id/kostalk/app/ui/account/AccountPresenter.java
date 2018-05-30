package id.kostalk.app.ui.account;

import javax.inject.Inject;

import id.kostalk.app.data.DataManager;
import id.kostalk.app.data.remote.model.RootResponse;
import id.kostalk.app.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AccountPresenter<V extends AccountMvpView> extends BasePresenter<V> implements AccountMvpPresenter<V> {

    @Inject
    public AccountPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onGetPemilik() {
        getMvpView().showLoading();

        getDataManager().doGetOwnerProfileApiCall(getDataManager().getCurrentUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootResponse>() {
                    @Override
                    public void accept(RootResponse response) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().onResultPemilik(response.getPemilik());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().hideLoading();
                        handleApiError(throwable);
                    }
                });
    }
}
