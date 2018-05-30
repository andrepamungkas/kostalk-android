package id.kostalk.app.ui.building_form;

import android.text.TextUtils;

import javax.inject.Inject;

import id.kostalk.app.R;
import id.kostalk.app.data.DataManager;
import id.kostalk.app.data.remote.model.BuildingRequest;
import id.kostalk.app.data.remote.model.RootResponse;
import id.kostalk.app.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BuildingFormPresenter<V extends BuildingFormMvpView> extends BasePresenter<V> implements BuildingFormMvpPresenter<V> {

    @Inject
    public BuildingFormPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onAddClick(String name) {
        if (TextUtils.isEmpty(name)) {
            getMvpView().onError(R.string.empty_name);
            return;
        }
        getMvpView().showLoading();

        getDataManager().doAddBuildingApiCall(getDataManager().getCurrentUserId(), new BuildingRequest.Add(name))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootResponse>() {
                    @Override
                    public void accept(RootResponse response) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().reopenBuildingActivity();
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
    public void onUpdateClick(long buildingId, String name) {
        if (TextUtils.isEmpty(name)) {
            getMvpView().onError(R.string.empty_name);
            return;
        }
        getMvpView().showLoading();

        getDataManager().doUpdateBuildingApiCall(getDataManager().getCurrentUserId(), buildingId, new BuildingRequest.Update(name))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootResponse>() {
                    @Override
                    public void accept(RootResponse response) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().hideKeyboard();
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

    @Override
    public void onDeleteClick(long buildingId) {
        getMvpView().showLoading();

        getDataManager().doDeleteBuildingApiCall(getDataManager().getCurrentUserId(), buildingId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootResponse>() {
                    @Override
                    public void accept(RootResponse response) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().reopenBuildingActivity();
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
