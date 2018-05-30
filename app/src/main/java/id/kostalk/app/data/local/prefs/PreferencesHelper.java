package id.kostalk.app.data.local.prefs;

import id.kostalk.app.data.DataManager;

/**
 * Created by andre on 3/23/2018.
 */

public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    String getCurrentUserEmail();

    void setCurrentUserEmail(String email);

    String getAccessToken();

    void setAccessToken(String accessToken);
}
