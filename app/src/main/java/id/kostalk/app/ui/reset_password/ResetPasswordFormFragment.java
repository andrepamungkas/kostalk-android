package id.kostalk.app.ui.reset_password;


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
import id.kostalk.app.data.remote.model.RootResponse;
import id.kostalk.app.ui.base.BaseFragment;

public class ResetPasswordFormFragment extends BaseFragment implements ResetPasswordFormMvpView {

    private static final String ARGS_PHONE = "id.ekos.app.ui.reset_password.ResetPasswordFormFragment.ARGS_PHONE";
    ResetPasswordFormFragment.OnResetPasswordSuccessListener mCallback;
    @Inject
    ResetPasswordFormMvpPresenter<ResetPasswordFormMvpView> mPresenter;
    @BindView(R2.id.input_code)
    EditText mInputCode;
    @BindView(R2.id.input_new_password)
    EditText mInputNewPassword;

    public static ResetPasswordFormFragment newInstance() {
        Bundle args = new Bundle();
        ResetPasswordFormFragment fragment = new ResetPasswordFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ResetPasswordFormFragment newInstance(String phone) {
        Bundle args = new Bundle();
        args.putString(ARGS_PHONE, phone);
        ResetPasswordFormFragment fragment = new ResetPasswordFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (ResetPasswordFormFragment.OnResetPasswordSuccessListener) getBaseActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getBaseActivity().toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_password_form, container, false);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
        setUp(view);
        return view;
    }

    @Override
    protected void setUp(View view) {

    }

    @OnClick(R2.id.button_reset_password)
    void onRequestResetPasswordClick() {
        if (getArguments().containsKey(ARGS_PHONE)) {
            String phone = getArguments().getString(ARGS_PHONE);
            String code = mInputCode.getText().toString();
            String newPassword = mInputNewPassword.getText().toString();
            mPresenter.onResetPasswordClick(phone, code, newPassword);
        }
    }

    @Override
    public void showResetPasswordResult(RootResponse response) {
        showSnackBar(response.getMessage());
        if (response.getSuccess()) mCallback.onResetPasswordSuccess();
    }

    public interface OnResetPasswordSuccessListener {
        void onResetPasswordSuccess();
    }
}
