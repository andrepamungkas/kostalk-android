package id.kostalk.app.ui.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kostalk.app.R;
import id.kostalk.app.R2;
import id.kostalk.app.data.remote.model.Pemilik;
import id.kostalk.app.ui.base.BaseActivity;
import id.kostalk.app.ui.building.BuildingActivity;
import id.kostalk.app.ui.email_form.EmailFormActivity;
import id.kostalk.app.ui.phone_form.PhoneFormActivity;
import id.kostalk.app.ui.withdraw.WithdrawActivity;

public class AccountActivity extends BaseActivity implements AccountMvpView {

    @Inject
    AccountMvpPresenter<AccountMvpView> mPresenter;

    @BindView(R2.id.text_name)
    TextView mTextName;

    @BindView(R2.id.text_saldo)
    TextView mTextSaldo;

    @BindView(R2.id.text_profile_name)
    TextView mTextProfileName;

    @BindView(R2.id.text_profile_noHp)
    TextView mTextProfileNoHp;

    @BindView(R2.id.text_profile_email)
    TextView mTextProfileEmail;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AccountActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onGetPemilik();
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void onResultPemilik(Pemilik pemilik) {
        mTextName.setText(pemilik.getNama());
        mTextSaldo.setText(pemilik.getSaldo());
        mTextProfileName.setText(pemilik.getNama());
        mTextProfileNoHp.setText(pemilik.getNoHp());
        mTextProfileEmail.setText(pemilik.getEmail());
    }

    @OnClick(R2.id.button_update_profile)
    void onUpdateProfileClick() {
        startActivity(PhoneFormActivity.getStartIntent(this, mTextName.getText().toString(), mTextProfileNoHp.getText().toString(), mTextProfileEmail.getText().toString()));
    }
}
