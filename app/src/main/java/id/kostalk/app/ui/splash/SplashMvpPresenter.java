package id.kostalk.app.ui.splash;

import id.kostalk.app.di.PerActivity;
import id.kostalk.app.ui.base.MvpPresenter;

/**
 * Created by andre on 3/24/2018.
 */

@PerActivity
public interface SplashMvpPresenter <V extends SplashMvpView> extends MvpPresenter<V> {
}
