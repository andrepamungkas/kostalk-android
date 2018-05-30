package id.kostalk.app.ui.phone_form;

import javax.inject.Inject;

import id.kostalk.app.R;
import id.kostalk.app.data.DataManager;
import id.kostalk.app.data.remote.model.LoginRequest;
import id.kostalk.app.data.remote.model.PemilikRequest;
import id.kostalk.app.data.remote.model.RootResponse;
import id.kostalk.app.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PhoneFormPresenter<V extends PhoneFormMvpView> extends BasePresenter<V> implements PhoneFormMvpPresenter<V> {

    @Inject
    public PhoneFormPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onSaveClick(String nama, String email, String noHp) {
        getMvpView().showLoading();

        getDataManager().doUpdateOwnerProfileApiCall(getDataManager().getCurrentUserId(), new PemilikRequest(nama, noHp, email))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootResponse>() {
                    @Override
                    public void accept(RootResponse response) throws Exception {
                        getMvpView().showSnackBar(response.getMessage());
                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().onError("email/no Hp sudah terdaftar");
                    }
                });
    }
}
