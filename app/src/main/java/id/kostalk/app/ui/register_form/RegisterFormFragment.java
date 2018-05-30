package id.kostalk.app.ui.register_form;

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

public class RegisterFormFragment extends BaseFragment implements RegisterFormMvpView {

    OnRegisterSuccessListener mCallback;

    public interface OnRegisterSuccessListener {
        void onRegisterSuccess(String phone);
    }

    @Inject
    RegisterFormMvpPresenter<RegisterFormMvpView> mPresenter;

    @BindView(R2.id.input_name)
    EditText mInputName;

    @BindView(R2.id.input_phone)
    EditText mInputPhone;

    @BindView(R2.id.input_email)
    EditText mInputEmail;

//    @BindView(R2.id.input_password)
//    EditText mInputPassword;

    public static RegisterFormFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFormFragment fragment = new RegisterFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnRegisterSuccessListener) getBaseActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getBaseActivity().toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_form, container, false);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
        setUp(view);
        return view;
    }

    @Override
    protected void setUp(View view) {

    }

    @OnClick(R2.id.button_register)
    void onRegisterClick() {
        String name = mInputName.getText().toString();
        String phone = mInputPhone.getText().toString();
        String email = mInputEmail.getText().toString();
        mPresenter.onRegisterClick(name, phone, email);
    }

    @Override
    public void showRegisterResult(RootResponse response) {
        showSnackBar(response.getMessage());
        if (response.getSuccess()) mCallback.onRegisterSuccess(response.getPemilik().getNoHp());
    }
}
