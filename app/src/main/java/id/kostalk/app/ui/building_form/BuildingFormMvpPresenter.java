package id.kostalk.app.ui.building_form;

import id.kostalk.app.di.PerActivity;
import id.kostalk.app.ui.base.MvpPresenter;

@PerActivity
public interface BuildingFormMvpPresenter<V extends BuildingFormMvpView> extends MvpPresenter<V> {

    void onAddClick(String name);

    void onUpdateClick(long buildingId, String name);

    void onDeleteClick(long buildingId);
}
