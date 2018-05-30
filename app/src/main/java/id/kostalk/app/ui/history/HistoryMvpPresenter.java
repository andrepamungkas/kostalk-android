package id.kostalk.app.ui.history;

import id.kostalk.app.di.PerActivity;
import id.kostalk.app.ui.base.MvpPresenter;

@PerActivity
public interface HistoryMvpPresenter <V extends HistoryMvpView> extends MvpPresenter<V> {

    void onGetHistoryList(long buildingId);
}
