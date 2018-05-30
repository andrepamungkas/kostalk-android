package id.kostalk.app.ui.base;

import android.support.annotation.StringRes;

/**
 * Created by andre on 3/23/2018.
 */

public interface MvpView {

    void showLoading();

    void hideLoading();

    void openActivityOnTokenExpire();

    void onError(@StringRes int resId);

    void onError(String message);

    void showSnackBar(String message);

    boolean isNetworkConnected();

    void hideKeyboard();
}
