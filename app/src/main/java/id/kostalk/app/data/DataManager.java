package id.kostalk.app.data;

import id.kostalk.app.data.local.prefs.PreferencesHelper;
import id.kostalk.app.data.remote.ApiHelper;

/**
 * Created by andre on 3/23/2018.
 */

public interface DataManager extends /*DbHelper, */ApiHelper, PreferencesHelper {

    void updateApiHeader(String accessToken, Long userId);

    void setUserAsLoggedOut();

    void updateUserInfo(
            LoggedInMode loggedInMode,
            String accessToken,
            Long userId,
            String userName);

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0), LOGGED_IN_MODE_GOOGLE(1), LOGGED_IN_MODE_SERVER(2);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}