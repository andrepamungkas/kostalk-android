package id.kostalk.app.ui.account;

import id.kostalk.app.data.remote.model.Pemilik;
import id.kostalk.app.ui.base.MvpView;

public interface AccountMvpView extends MvpView {

    void onResultPemilik(Pemilik pemilik);
}
