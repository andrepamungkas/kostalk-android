package id.kostalk.app.ui.history;

import java.text.ParseException;
import java.util.List;

import id.kostalk.app.data.remote.model.History;
import id.kostalk.app.ui.base.MvpView;

public interface HistoryMvpView extends MvpView {

    void buildHistoryList(List<History> historyList);
}
