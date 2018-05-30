package id.kostalk.app.di.component;

import dagger.Component;
import id.kostalk.app.di.PerService;
import id.kostalk.app.di.module.ServiceModule;

/**
 * Created by andre on 3/23/2018.
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

//    void inject(GoogleLocationService googleLocationService);
}