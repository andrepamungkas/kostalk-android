package id.kostalk.app.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kostalk.app.R;
import id.kostalk.app.R2;
import id.kostalk.app.data.remote.model.Building;
import id.kostalk.app.ui.account.AccountActivity;
import id.kostalk.app.ui.base.BaseActivity;
import id.kostalk.app.ui.history.HistoryFragment;
import id.kostalk.app.ui.member.MemberFragment;
import id.kostalk.app.ui.member_form.MemberFormActivity;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.tabs)
    TabLayout mLayoutTab;

    @BindView(R2.id.view_pager)
    ViewPager mViewPager;

    List<Building> mBuildingList = new ArrayList<Building>();

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mPresenter.onGetBuildingList();
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
                    setViewPagerAdapter(adapter);
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);

//        mSpinnerBuilding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            int time = 0;
//
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (time == 0) {
//                } else {
//                    long selectedBuildingId = ((Building) mSpinnerBuilding.getSelectedItem()).getId();
//                    adapter.updateBuilding(selectedBuildingId);
//                }
//                time = 1;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
    }

    @Override
    public void buildSpinnerBuilding(List<Building> buildingList) {
        mBuildingList = buildingList;
        ArrayAdapter<Building> dataAdapter = new ArrayAdapter<>(this,
                R.layout.item_spinner_building, buildingList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mSpinnerBuilding.setAdapter(dataAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_profile:
                startActivity(AccountActivity.getStartIntent(this));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setViewPagerAdapter(ViewPagerAdapter adapter) {
        long selectedBuildingId = 0;
        adapter.addFragment(MemberFragment.newInstance(selectedBuildingId, new ArrayList<Building>(mBuildingList)), "Anggota");
//        adapter.addFragment(HistoryFragment.newInstance(selectedBuildingId), "Riwayat");
        mViewPager.setAdapter(adapter);
        mLayoutTab.setupWithViewPager(mViewPager);
    }

    @OnClick(R2.id.button_add_member)
    void onAddMemberClick() {
        startActivity(MemberFormActivity.getStartIntent(this, new ArrayList<Building>(mBuildingList)));
    }
}
