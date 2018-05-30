package id.kostalk.app;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import id.kostalk.app.data.DataManager;
import id.kostalk.app.di.component.ApplicationComponent;
import id.kostalk.app.di.component.DaggerApplicationComponent;
import id.kostalk.app.di.module.ApplicationModule;

/**
 * Created by andre on 3/23/2018.
 */

public class App extends Application {

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }
}
