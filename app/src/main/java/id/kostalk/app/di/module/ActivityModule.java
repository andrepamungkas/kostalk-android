package id.kostalk.app.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import id.kostalk.app.di.ActivityContext;
import id.kostalk.app.di.PerActivity;
import id.kostalk.app.ui.account.AccountMvpPresenter;
import id.kostalk.app.ui.account.AccountMvpView;
import id.kostalk.app.ui.account.AccountPresenter;
import id.kostalk.app.ui.building.BuildingMvpPresenter;
import id.kostalk.app.ui.building.BuildingMvpView;
import id.kostalk.app.ui.building.BuildingPresenter;
import id.kostalk.app.ui.building_form.BuildingFormMvpPresenter;
import id.kostalk.app.ui.building_form.BuildingFormMvpView;
import id.kostalk.app.ui.building_form.BuildingFormPresenter;
import id.kostalk.app.ui.email_form.EmailFormMvpPresenter;
import id.kostalk.app.ui.email_form.EmailFormMvpView;
import id.kostalk.app.ui.email_form.EmailFormPresenter;
import id.kostalk.app.ui.history.HistoryMvpPresenter;
import id.kostalk.app.ui.history.HistoryMvpView;
import id.kostalk.app.ui.history.HistoryPresenter;
import id.kostalk.app.ui.login.LoginMvpPresenter;
import id.kostalk.app.ui.login.LoginMvpView;
import id.kostalk.app.ui.login.LoginPresenter;
import id.kostalk.app.ui.login_form.LoginFormMvpPresenter;
import id.kostalk.app.ui.login_form.LoginFormMvpView;
import id.kostalk.app.ui.login_form.LoginFormPresenter;
import id.kostalk.app.ui.main.MainMvpPresenter;
import id.kostalk.app.ui.main.MainMvpView;
import id.kostalk.app.ui.main.MainPresenter;
import id.kostalk.app.ui.member.MemberMvpPresenter;
import id.kostalk.app.ui.member.MemberMvpView;
import id.kostalk.app.ui.member.MemberPresenter;
import id.kostalk.app.ui.member_form.MemberFormMvpPresenter;
import id.kostalk.app.ui.member_form.MemberFormMvpView;
import id.kostalk.app.ui.member_form.MemberFormPresenter;
import id.kostalk.app.ui.member_profile.MemberProfileMvpPresenter;
import id.kostalk.app.ui.member_profile.MemberProfileMvpView;
import id.kostalk.app.ui.member_profile.MemberProfilePresenter;
import id.kostalk.app.ui.phone_form.PhoneFormMvpPresenter;
import id.kostalk.app.ui.phone_form.PhoneFormMvpView;
import id.kostalk.app.ui.phone_form.PhoneFormPresenter;
import id.kostalk.app.ui.register_form.RegisterFormMvpPresenter;
import id.kostalk.app.ui.register_form.RegisterFormMvpView;
import id.kostalk.app.ui.register_form.RegisterFormPresenter;
import id.kostalk.app.ui.request_reset_password.RequestResetPasswordFormMvpPresenter;
import id.kostalk.app.ui.request_reset_password.RequestResetPasswordFormMvpView;
import id.kostalk.app.ui.request_reset_password.RequestResetPasswordFormPresenter;
import id.kostalk.app.ui.reset_password.ResetPasswordFormMvpPresenter;
import id.kostalk.app.ui.reset_password.ResetPasswordFormMvpView;
import id.kostalk.app.ui.reset_password.ResetPasswordFormPresenter;
import id.kostalk.app.ui.splash.SplashMvpPresenter;
import id.kostalk.app.ui.splash.SplashMvpView;
import id.kostalk.app.ui.splash.SplashPresenter;
import id.kostalk.app.ui.withdraw.WithdrawMvpPresenter;
import id.kostalk.app.ui.withdraw.WithdrawMvpView;
import id.kostalk.app.ui.withdraw.WithdrawPresenter;

/**
 * Created by andre on 3/23/2018.
 */

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    LinearLayoutManager provideLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    AccountMvpPresenter<AccountMvpView> provideAccountPresenter(AccountPresenter<AccountMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MemberFormMvpPresenter<MemberFormMvpView> provideMemberFormPresenter(MemberFormPresenter<MemberFormMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MemberProfileMvpPresenter<MemberProfileMvpView> provideMemberProfilePresenter(MemberProfilePresenter<MemberProfileMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    WithdrawMvpPresenter<WithdrawMvpView> provideWithdrawPresenter(WithdrawPresenter<WithdrawMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    BuildingMvpPresenter<BuildingMvpView> provideBuildingPresenter(BuildingPresenter<BuildingMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    BuildingFormMvpPresenter<BuildingFormMvpView> provideBuildingFormPresenter(BuildingFormPresenter<BuildingFormMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    PhoneFormMvpPresenter<PhoneFormMvpView> providePhoneFormPresenter(PhoneFormPresenter<PhoneFormMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    EmailFormMvpPresenter<EmailFormMvpView> provideEmailFormPresenter(EmailFormPresenter<EmailFormMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    RegisterFormMvpPresenter<RegisterFormMvpView> provideRegisterFormPresenter(RegisterFormPresenter<RegisterFormMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginFormMvpPresenter<LoginFormMvpView> provideLoginFormPresenter(LoginFormPresenter<LoginFormMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    RequestResetPasswordFormMvpPresenter<RequestResetPasswordFormMvpView> provideRequestResetPasswordFormPresenter(RequestResetPasswordFormPresenter<RequestResetPasswordFormMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ResetPasswordFormMvpPresenter<ResetPasswordFormMvpView> provideResetPasswordFormPresenter(ResetPasswordFormPresenter<ResetPasswordFormMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MemberMvpPresenter<MemberMvpView> provideMemberPresenter(MemberPresenter<MemberMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    HistoryMvpPresenter<HistoryMvpView> provideHistoryPresenter(HistoryPresenter<HistoryMvpView> presenter) {
        return presenter;
    }
}
