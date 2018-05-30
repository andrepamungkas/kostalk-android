package id.kostalk.app.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.kostalk.app.BuildConfig;
import id.kostalk.app.data.AppDataManager;
import id.kostalk.app.data.DataManager;
import id.kostalk.app.data.local.prefs.AppPreferencesHelper;
import id.kostalk.app.data.local.prefs.PreferencesHelper;
import id.kostalk.app.data.remote.ApiCall;
import id.kostalk.app.data.remote.ApiHeader;
import id.kostalk.app.data.remote.ApiHelper;
import id.kostalk.app.data.remote.ApiInterceptor;
import id.kostalk.app.data.remote.AppApiHelper;
import id.kostalk.app.di.ApiInfo;
import id.kostalk.app.di.ApplicationContext;
import id.kostalk.app.di.DatabaseInfo;
import id.kostalk.app.di.PreferenceInfo;
import id.kostalk.app.utils.Constants;

/**
 * Created by andre on 3/23/2018.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return Constants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return Constants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

//    @Provides
//    @Singleton
//    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
//        return appDbHelper;
//    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiCall provideApiCall(ApiInterceptor apiInterceptor) {
        return ApiCall.Factory.create(apiInterceptor);
    }

    @Provides
    @Singleton
    ApiHeader provideApiHeader(@ApiInfo String apiKey, PreferencesHelper preferencesHelper) {
        return new ApiHeader(apiKey, preferencesHelper.getCurrentUserId(), preferencesHelper.getAccessToken());
    }
}