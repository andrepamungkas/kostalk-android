package id.kostalk.app.ui.email_form;

import javax.inject.Inject;

import id.kostalk.app.data.DataManager;
import id.kostalk.app.ui.base.BasePresenter;

public class EmailFormPresenter<V extends EmailFormMvpView> extends BasePresenter<V> implements EmailFormMvpPresenter<V> {

    @Inject
    public EmailFormPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
