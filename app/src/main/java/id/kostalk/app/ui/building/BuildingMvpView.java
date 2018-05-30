package id.kostalk.app.ui.building;

import java.util.List;

import id.kostalk.app.data.remote.model.Building;
import id.kostalk.app.ui.base.MvpView;

public interface BuildingMvpView extends MvpView {

    void buildBuildingList(List<Building> buildingList);
}
