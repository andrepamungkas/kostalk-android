package id.kostalk.app.ui.email_form;

import id.kostalk.app.di.PerActivity;
import id.kostalk.app.ui.base.MvpPresenter;

@PerActivity
public interface EmailFormMvpPresenter<V extends EmailFormMvpView> extends MvpPresenter<V> {
}
