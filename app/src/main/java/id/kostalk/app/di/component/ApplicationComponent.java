package id.kostalk.app.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import id.kostalk.app.App;
import id.kostalk.app.data.DataManager;
import id.kostalk.app.di.ApplicationContext;
import id.kostalk.app.di.module.ApplicationModule;

/**
 * Created by andre on 3/23/2018.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}