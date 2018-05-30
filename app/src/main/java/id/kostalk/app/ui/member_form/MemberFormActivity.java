package id.kostalk.app.ui.member_form;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kostalk.app.R;
import id.kostalk.app.R2;
import id.kostalk.app.data.remote.model.Building;
import id.kostalk.app.data.remote.model.Anggota;
import id.kostalk.app.ui.base.BaseActivity;

public class MemberFormActivity extends BaseActivity implements MemberFormMvpView, DatePickerDialog.OnDateSetListener {

    private static final String EXTRA_BUILDING_ID = "id.ekos.app.ui.member_form.MemberFormActivity.EXTRA_BUILDING_ID";
    private static final String EXTRA_BUILDING = "id.ekos.app.ui.member_form.MemberFormActivity.EXTRA_BUILDING";
    private static final String EXTRA_MEMBER = "id.ekos.app.ui.member_form.MemberFormActivity.EXTRA_MEMBER";

    @Inject
    MemberFormMvpPresenter<MemberFormMvpView> mPresenter;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.text_toolbar)
    TextView mTextToolbar;

//    @BindView(R2.id.spinner_building)
//    Spinner mSpinnerBuilding;

    @BindView(R2.id.input_name)
    EditText mInputName;

    @BindView(R2.id.input_phone)
    EditText mInputPhone;

    @BindView(R2.id.input_cost)
    EditText mInputCost;

    @BindView(R2.id.input_interval)
    EditText mInputInterval;

    @BindView(R2.id.input_start_date)
    EditText mInputStartDate;

    @BindView(R2.id.button_add)
    Button mButtonAdd;

    @BindView(R2.id.button_update)
    Button mButtonUpdate;

    Calendar mCalendar = Calendar.getInstance();
    String[] mMonthName;
    String startDate;
    long mBuildingId;
    List<Building> mBuildingList;
    Anggota mAnggota;

    public static Intent getStartIntent(Context context, ArrayList<Building> buildingList) {
        Intent intent = new Intent(context, MemberFormActivity.class);
        intent.putExtra(EXTRA_BUILDING, buildingList);
        return intent;
    }

    public static Intent getStartIntent(Context context, long buildingId, ArrayList<Building> buildingList, Anggota anggota) {
        Intent intent = new Intent(context, MemberFormActivity.class);
        intent.putExtra(EXTRA_BUILDING_ID, buildingId);
        intent.putExtra(EXTRA_BUILDING, buildingList);
        intent.putExtra(EXTRA_MEMBER, anggota);
        return intent;
    }

    public static Intent getStartIntent(Context context, Anggota anggota) {
        Intent intent = new Intent(context, MemberFormActivity.class);
        intent.putExtra(EXTRA_MEMBER, anggota);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_form);

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

        mMonthName = getResources().getStringArray(R.array.array_month);
        updateStartDate(mCalendar);

        mBuildingList = (ArrayList<Building>) getIntent().getSerializableExtra(EXTRA_BUILDING);
        ArrayAdapter<Building> dataAdapter = new ArrayAdapter<>(this,
                R.layout.item_spinner_building_member_form, mBuildingList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mSpinnerBuilding.setAdapter(dataAdapter);

        if (getIntent().hasExtra(EXTRA_MEMBER)) {
            mBuildingId = getIntent().getLongExtra(EXTRA_BUILDING_ID, 0);
            mAnggota = (Anggota) getIntent().getSerializableExtra(EXTRA_MEMBER);
            mButtonAdd.setVisibility(View.GONE);
            mTextToolbar.setText(R.string.anggota);
            mInputName.setText(mAnggota.getNama());
            mInputPhone.setText(mAnggota.getNoHp());
            mInputCost.setText(mAnggota.getBiaya().toString());
//            mInputInterval.setText(mAnggota.getInterval().toString());
            try {
                SimpleDateFormat fromServer = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", new Locale("id"));
                mCalendar.setTime(fromServer.parse(mAnggota.getTagihan().getStart()));
                updateStartDate(mCalendar);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            mButtonUpdate.setVisibility(View.GONE);
        }
    }

    @Override
    public void reopenMainActivity() {
        finish();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, day);
        updateStartDate(mCalendar);
    }

    void updateStartDate(Calendar calendar) {
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        int month = mCalendar.get(Calendar.MONTH);
        int year = mCalendar.get(Calendar.YEAR);

        String startDate = day + " " + mMonthName[month] + " " + year;
        this.startDate = year + "-" + (month + 1) + "-" + day;
        mInputStartDate.setText(startDate);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R2.id.input_start_date)
    void onInputStartDateClick() {
        new DatePickerDialog(this, this, mCalendar
                .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick(R2.id.button_add)
    void onAddClick() {
//        long buildingId = ((Building) mSpinnerBuilding.getSelectedItem()).getId();
        String name = mInputName.getText().toString();
        String phone = mInputPhone.getText().toString();
        int cost = Integer.valueOf(mInputCost.getText().toString());
        int interval = Integer.valueOf(mInputInterval.getText().toString());
        mPresenter.onAddClick(name, phone, cost, interval, startDate);
//        reopenMainActivity();
    }

    @OnClick(R2.id.button_update)
    void onUpdateClick() {
        String name = mInputName.getText().toString();
        String phone = mInputPhone.getText().toString();
        int cost = Integer.valueOf(mInputCost.getText().toString());
        int interval = Integer.valueOf(mInputInterval.getText().toString());
        mPresenter.onUpdateClick(mAnggota.getId(), name, phone, cost, interval, startDate);
//        reopenMainActivity();
    }
}
