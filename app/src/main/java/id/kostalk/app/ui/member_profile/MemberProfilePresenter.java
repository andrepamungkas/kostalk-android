package id.kostalk.app.ui.member_profile;

import javax.inject.Inject;

import id.kostalk.app.data.DataManager;
import id.kostalk.app.data.remote.model.RootResponse;
import id.kostalk.app.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MemberProfilePresenter<V extends MemberProfileMvpView> extends BasePresenter<V> implements MemberProfileMvpPresenter<V> {

    @Inject
    public MemberProfilePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onDeleteClick(long memberId) {
        getMvpView().showLoading();

        getDataManager().doDeleteMemberApiCall(getDataManager().getCurrentUserId(), memberId)
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
}
