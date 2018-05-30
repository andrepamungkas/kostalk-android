package id.kostalk.app.ui.member_form;

import android.text.TextUtils;

import javax.inject.Inject;

import id.kostalk.app.R;
import id.kostalk.app.data.DataManager;
import id.kostalk.app.data.remote.model.MemberRequest;
import id.kostalk.app.data.remote.model.RootResponse;
import id.kostalk.app.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MemberFormPresenter<V extends MemberFormMvpView> extends BasePresenter<V> implements MemberFormMvpPresenter<V> {

    @Inject
    public MemberFormPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onAddClick(String name, String phone, int cost, int interval, String startDate) {
        if (TextUtils.isEmpty(name)) {
            getMvpView().onError(R.string.empty_name);
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            getMvpView().onError(R.string.empty_phone);
            return;
        }
        if (TextUtils.isEmpty(String.valueOf(cost))) {
            getMvpView().onError(R.string.empty_code);
            return;
        }
        if (TextUtils.isEmpty(String.valueOf(interval))) {
            getMvpView().onError(R.string.empty_interval);
            return;
        }
        if (TextUtils.isEmpty(startDate)) {
            getMvpView().onError(R.string.empty_start_date);
            return;
        }
        getMvpView().showLoading();

        getDataManager().doAddMemberApiCall(getDataManager().getCurrentUserId(), new MemberRequest(name, phone, cost, interval, startDate))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootResponse>() {
                    @Override
                    public void accept(RootResponse response) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().reopenMainActivity();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().hideLoading();
                        handleApiError(throwable);
                    }
                });
    }

    @Override
    public void onUpdateClick(long memberId, String name, String phone, int cost, int interval, String startDate) {
        if (TextUtils.isEmpty(name)) {
            getMvpView().onError(R.string.empty_name);
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            getMvpView().onError(R.string.empty_phone);
            return;
        }
        if (TextUtils.isEmpty(String.valueOf(cost))) {
            getMvpView().onError(R.string.empty_code);
            return;
        }
        if (TextUtils.isEmpty(String.valueOf(interval))) {
            getMvpView().onError(R.string.empty_interval);
            return;
        }
        if (TextUtils.isEmpty(startDate)) {
            getMvpView().onError(R.string.empty_start_date);
            return;
        }
        getMvpView().showLoading();

        getDataManager().doUpdateMemberApiCall(getDataManager().getCurrentUserId(), memberId, new MemberRequest(name, phone, cost, interval, startDate))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootResponse>() {
                    @Override
                    public void accept(RootResponse response) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().showSnackBar(response.getMessage());
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
