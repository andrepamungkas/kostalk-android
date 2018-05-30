package id.kostalk.app.di.component;

import dagger.Component;
import id.kostalk.app.di.PerActivity;
import id.kostalk.app.di.module.ActivityModule;
import id.kostalk.app.ui.account.AccountActivity;
import id.kostalk.app.ui.building.BuildingActivity;
import id.kostalk.app.ui.building_form.BuildingFormActivity;
import id.kostalk.app.ui.email_form.EmailFormActivity;
import id.kostalk.app.ui.history.HistoryFragment;
import id.kostalk.app.ui.login.LoginActivity;
import id.kostalk.app.ui.login_form.LoginFormFragment;
import id.kostalk.app.ui.main.MainActivity;
import id.kostalk.app.ui.member.MemberFragment;
import id.kostalk.app.ui.member_form.MemberFormActivity;
import id.kostalk.app.ui.member_profile.MemberProfileActivity;
import id.kostalk.app.ui.phone_form.PhoneFormActivity;
import id.kostalk.app.ui.register_form.RegisterFormFragment;
import id.kostalk.app.ui.request_reset_password.RequestResetPasswordFormFragment;
import id.kostalk.app.ui.reset_password.ResetPasswordFormFragment;
import id.kostalk.app.ui.splash.SplashActivity;
import id.kostalk.app.ui.withdraw.WithdrawActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(LoginActivity activity);

    void inject(MainActivity activity);

    void inject(AccountActivity activity);

    void inject(MemberFormActivity activity);

    void inject(MemberProfileActivity activity);

    void inject(WithdrawActivity activity);

    void inject(BuildingActivity activity);

    void inject(BuildingFormActivity activity);

    void inject(PhoneFormActivity activity);

    void inject(EmailFormActivity activity);

    void inject(RegisterFormFragment fragment);

    void inject(LoginFormFragment fragment);

    void inject(RequestResetPasswordFormFragment fragment);

    void inject(ResetPasswordFormFragment fragment);

    void inject(MemberFragment fragment);

    void inject(HistoryFragment fragment);
}