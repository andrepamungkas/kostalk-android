package id.kostalk.app.ui.login_form;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kostalk.app.R;
import id.kostalk.app.R2;
import id.kostalk.app.ui.base.BaseFragment;
import id.kostalk.app.ui.main.MainActivity;

public class LoginFormFragment extends BaseFragment implements LoginFormMvpView {

    LoginFormFragment.OnLoginFormListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (LoginFormFragment.OnLoginFormListener) getBaseActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getBaseActivity().toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Inject
    LoginFormMvpPresenter<LoginFormMvpView> mPresenter;

    @BindView(R2.id.input_username)
    EditText mInputUsername;

    public static LoginFormFragment newInstance() {
        Bundle args = new Bundle();
        LoginFormFragment fragment = new LoginFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

//    @OnClick(R2.id.button_forgot_password)
//    void onForgotPasswordClick() {
//        mCallback.onRequestResetPasswordClick();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_form, container, false);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
        setUp(view);
        return view;
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void openMainActivity() {
        startActivity(MainActivity.getStartIntent(getBaseActivity()));
        getBaseActivity().finish();
    }

    @Override
    public void openRequestResetPassword(String kunci) {
        mCallback.onRequestResetPasswordClick(kunci);
    }

    @OnClick(R2.id.button_login)
    void onLoginClick() {
        String usename = mInputUsername.getText().toString();
        mPresenter.onLoginClick(usename);
    }

    public interface OnLoginFormListener {
        void onRequestResetPasswordClick(String kunci);
    }
}
