package id.kostalk.app.ui.phone_form;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kostalk.app.R;
import id.kostalk.app.R2;
import id.kostalk.app.ui.base.BaseActivity;

public class PhoneFormActivity extends BaseActivity implements PhoneFormMvpView {

    @Inject
    PhoneFormMvpPresenter<PhoneFormMvpView> mPresenter;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.input_nama)
    EditText mInputNama;

    @BindView(R2.id.input_nohp)
    EditText mInputNohp;

    @BindView(R2.id.input_email)
    EditText mInputEmail;

    public static Intent getStartIntent(Context context, String name, String phone, String email) {
        Intent intent = new Intent(context, PhoneFormActivity.class);
        intent.putExtra("nama", name);
        intent.putExtra("noHp", phone);
        intent.putExtra("email", email);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_form);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mInputNama.setText(getIntent().getStringExtra("nama"));
        mInputNohp.setText(getIntent().getStringExtra("noHp"));
        mInputEmail.setText(getIntent().getStringExtra("email"));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R2.id.button_save)
    void onSaveClick() {
        String nama = mInputNama.getText().toString();
        String noHp = mInputNohp.getText().toString();
        String email = mInputEmail.getText().toString();
        mPresenter.onSaveClick(nama, email, noHp);
    }
}
