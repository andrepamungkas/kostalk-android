package id.kostalk.app.ui.member;

import javax.inject.Inject;

import id.kostalk.app.data.DataManager;
import id.kostalk.app.data.remote.model.RootResponse;
import id.kostalk.app.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MemberPresenter<V extends MemberMvpView> extends BasePresenter<V> implements MemberMvpPresenter<V> {

    @Inject
    public MemberPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onGetMemberList() {
        getMvpView().showLoading();

        getDataManager().doGetMemberApiCall(getDataManager().getCurrentUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootResponse>() {
                    @Override
                    public void accept(RootResponse response) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().buildMemberList(response.getAnggotas());
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
