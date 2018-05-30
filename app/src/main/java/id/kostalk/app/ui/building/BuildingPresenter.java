package id.kostalk.app.ui.building;

import javax.inject.Inject;

import id.kostalk.app.data.DataManager;
import id.kostalk.app.data.remote.model.RootResponse;
import id.kostalk.app.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BuildingPresenter<V extends BuildingMvpView> extends BasePresenter<V> implements BuildingMvpPresenter<V> {

    @Inject
    public BuildingPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onGetBuildingList() {
        getMvpView().showLoading();

        getDataManager().doGetBuildingApiCall(getDataManager().getCurrentUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootResponse>() {
                    @Override
                    public void accept(RootResponse response) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().buildBuildingList(response.getBuildingList());
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
