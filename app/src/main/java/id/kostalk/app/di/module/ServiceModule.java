package id.kostalk.app.di.module;

import android.app.Service;

import dagger.Module;

/**
 * Created by andre on 3/23/2018.
 */

@Module
public class ServiceModule {

    private final Service service;

    public ServiceModule(Service service) {
        this.service = service;
    }
}