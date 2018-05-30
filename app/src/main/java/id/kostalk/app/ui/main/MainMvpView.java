package id.kostalk.app.ui.main;

import java.util.List;

import id.kostalk.app.data.remote.model.Building;
import id.kostalk.app.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void buildSpinnerBuilding(List<Building> buildingList);
}
