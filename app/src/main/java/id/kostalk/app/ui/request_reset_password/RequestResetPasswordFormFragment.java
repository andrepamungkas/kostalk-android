package id.kostalk.app.ui.request_reset_password;


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
import id.kostalk.app.ui.main.MainActivity;

public class RequestResetPasswordFormFragment extends BaseFragment implements RequestResetPasswordFormMvpView {

    RequestResetPasswordFormFragment.OnRequestResetPasswordSuccessListener mCallback;
    @Inject
    RequestResetPasswordFormMvpPresenter<RequestResetPasswordFormMvpView> mPresenter;
    @BindView(R2.id.input_phone)
    EditText mInputPhone;

    public static RequestResetPasswordFormFragment newInstance(String kunci) {
        Bundle args = new Bundle();
        RequestResetPasswordFormFragment fragment = new RequestResetPasswordFormFragment();
        args.putString("kunci", kunci);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (RequestResetPasswordFormFragment.OnRequestResetPasswordSuccessListener) getBaseActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getBaseActivity().toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_reset_password_form, container, false);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
        setUp(view);
        return view;
    }

    @Override
    protected void setUp(View view) {

    }

    @OnClick(R2.id.button_request_reset_password)
    void onRequestResetPasswordClick() {
//        String phone = mInputPhone.getText().toString();
        String kunci = getArguments().getString("kunci");
        String kode = mInputPhone.getText().toString();
        mPresenter.onRequestResetPasswordClick(kunci, kode);
        startActivity(MainActivity.getStartIntent(getBaseActivity()));
        getBaseActivity().finish();
    }

    @Override
    public void openMainActivity() {
        startActivity(MainActivity.getStartIntent(getBaseActivity()));
        getBaseActivity().finish();
    }

    @Override
    public void showRequestResetPasswordResult(RootResponse response) {
        showSnackBar(response.getMessage());
        if (response.getSuccess()) mCallback.onRequestResetPasswordSuccess(response.getOtp().getKunci());
    }

    public interface OnRequestResetPasswordSuccessListener {
        void onRequestResetPasswordSuccess(String phone);
    }
}
