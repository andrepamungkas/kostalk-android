package id.kostalk.app.ui.member_profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kostalk.app.R;
import id.kostalk.app.R2;
import id.kostalk.app.data.remote.model.Anggota;
import id.kostalk.app.data.remote.model.Building;
import id.kostalk.app.ui.base.BaseActivity;
import id.kostalk.app.ui.member_form.MemberFormActivity;
import id.kostalk.app.utils.CommonUtils;

public class MemberProfileActivity extends BaseActivity implements MemberProfileMvpView {

    private static final String EXTRA_BUILDING_ID = "id.ekos.app.ui.member_profile.MemberProfileActivity.EXTRA_BUILDING_ID";
    private static final String EXTRA_BUILDING = "id.ekos.app.ui.member_profile.MemberProfileActivity.EXTRA_BUILDING";
    private static final String EXTRA_MEMBER = "id.ekos.app.ui.member_profile.MemberProfileActivity.EXTRA_MEMBER";

    @Inject
    MemberProfileMvpPresenter<MemberProfileMvpView> mPresenter;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.text_name)
    TextView mTextName;

    @BindView(R2.id.text_building)
    TextView mTextBuilding;

    @BindView(R2.id.text_phone)
    TextView mTextPhone;

    @BindView(R2.id.text_cost)
    TextView mTextCost;

    @BindView(R2.id.text_interval)
    TextView mTextInterval;

    @BindView(R2.id.text_start_date)
    TextView mTextStartDate;

    @BindView(R2.id.text_end_date)
    TextView mTextEndDate;

    long mBuildingId;
//    ArrayList<Building> mBuildingList;
    Anggota mAnggota;

    public static Intent getStartIntent(Context context, long buildingId, Anggota anggota) {
        Intent intent = new Intent(context, MemberProfileActivity.class);
        intent.putExtra(EXTRA_BUILDING_ID, buildingId);
        intent.putExtra(EXTRA_MEMBER, anggota);
        return intent;
    }

    public static Intent getStartIntent(Context context, long buildingId, ArrayList<Building> buildingList, Anggota anggota) {
        Intent intent = new Intent(context, MemberProfileActivity.class);
        intent.putExtra(EXTRA_BUILDING_ID, buildingId);
        intent.putExtra(EXTRA_BUILDING, buildingList);
        intent.putExtra(EXTRA_MEMBER, anggota);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_profile);

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

        if (getIntent().hasExtra(EXTRA_MEMBER)) {
            mBuildingId = getIntent().getLongExtra(EXTRA_BUILDING_ID, 0);
//            mBuildingList = (ArrayList<Building>) getIntent().getSerializableExtra(EXTRA_BUILDING);
//            for (Building b : mBuildingList) {
//                if (b.getId() == mBuildingId)
//                    mTextBuilding.setText(b.getName());
//            }
            mAnggota = (Anggota) getIntent().getSerializableExtra(EXTRA_MEMBER);
            mTextName.setText(mAnggota.getNama());
            mTextPhone.setText(mAnggota.getNoHp());
            mTextCost.setText(CommonUtils.rupiahFormat(mAnggota.getBiaya()));
            mTextInterval.setText("1");
            SimpleDateFormat fromServer = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy", new Locale("id"));
            Log.d("tanggl mulai", mAnggota.getTagihan().getStart());
            try {
                String startDate = format.format(fromServer.parse(mAnggota.getTagihan().getStart()));
                String endDate = format.format(fromServer.parse(mAnggota.getTagihan().getEnd()));
                mTextStartDate.setText(startDate);
                mTextEndDate.setText(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void reopenMainActivity() {
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R2.id.button_update)
    void onUpdateClick() {
        startActivity(MemberFormActivity.getStartIntent(this, mAnggota));
    }

    @OnClick(R2.id.button_delete)
    void onDeleteClick() {
        mPresenter.onDeleteClick(mAnggota.getId());
    }
}
