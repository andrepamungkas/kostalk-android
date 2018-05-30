package id.kostalk.app.ui.building;

import id.kostalk.app.di.PerActivity;
import id.kostalk.app.ui.base.MvpPresenter;

@PerActivity
public interface BuildingMvpPresenter<V extends BuildingMvpView> extends MvpPresenter<V> {

    void onGetBuildingList();
}
