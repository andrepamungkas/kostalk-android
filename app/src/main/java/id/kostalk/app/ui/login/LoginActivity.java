package id.kostalk.app.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kostalk.app.R;
import id.kostalk.app.R2;
import id.kostalk.app.ui.base.BaseActivity;
import id.kostalk.app.ui.login_form.LoginFormFragment;
import id.kostalk.app.ui.register_form.RegisterFormFragment;
import id.kostalk.app.ui.request_reset_password.RequestResetPasswordFormFragment;
import id.kostalk.app.ui.reset_password.ResetPasswordFormFragment;

public class LoginActivity extends BaseActivity implements LoginMvpView,
        RegisterFormFragment.OnRegisterSuccessListener,
        LoginFormFragment.OnLoginFormListener,
        RequestResetPasswordFormFragment.OnRequestResetPasswordSuccessListener,
        ResetPasswordFormFragment.OnResetPasswordSuccessListener {

    @Inject
    LoginMvpPresenter<LoginMvpView> mPresenter;

    @BindView(R2.id.button_login_form)
    TextView mButtonLoginForm;

    @BindView(R2.id.button_register_form)
    TextView mButtonRegisterForm;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {

    }

    @OnClick(R2.id.button_login_form)
    void onLoginFormClick() {
        showLoginFormFragment();
    }

    @OnClick(R2.id.button_register_form)
    void onRegisterFormClick() {
        showRegisterFormFragment();
    }

    @Override
    public void showLoginFormFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_right, R.anim.slide_right_exit)
                .replace(R.id.layout_frame, LoginFormFragment.newInstance(), LoginFormFragment.class.getSimpleName())
                .commit();
        if (mButtonLoginForm.getVisibility() == View.VISIBLE) {
            mButtonLoginForm.setVisibility(View.GONE);
        }
        mButtonRegisterForm.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRegisterFormFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_left_exit)
                .replace(R.id.layout_frame, RegisterFormFragment.newInstance(), RegisterFormFragment.class.getSimpleName())
                .commit();
        if (mButtonRegisterForm.getVisibility() == View.VISIBLE) {
            mButtonRegisterForm.setVisibility(View.GONE);
        }
        mButtonLoginForm.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRequestResetPasswordFormFragment(String kunci) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_left_exit)
                .replace(R.id.layout_frame, RequestResetPasswordFormFragment.newInstance(kunci), RequestResetPasswordFormFragment.class.getSimpleName())
                .commit();
        mButtonRegisterForm.setVisibility(View.GONE);
        mButtonLoginForm.setVisibility(View.GONE);
    }

    @Override
    public void showResetPasswordFormFragment(String phone) {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_left_exit)
                .replace(R.id.layout_frame, ResetPasswordFormFragment.newInstance(phone), ResetPasswordFormFragment.class.getSimpleName())
                .commit();
        mButtonRegisterForm.setVisibility(View.GONE);
        mButtonLoginForm.setVisibility(View.GONE);
    }

    @Override
    public void onRegisterSuccess(String phone) {
        showLoginFormFragment();
    }

    @Override
    public void onRequestResetPasswordClick(String kunci) {
        showRequestResetPasswordFormFragment(kunci);
    }

    @Override
    public void onRequestResetPasswordSuccess(String phone) {
        showResetPasswordFormFragment(phone);
    }

    @Override
    public void onResetPasswordSuccess() {
        showLoginFormFragment();
    }
}
