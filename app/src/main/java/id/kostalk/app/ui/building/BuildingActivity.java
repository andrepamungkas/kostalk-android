package id.kostalk.app.ui.building;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kostalk.app.R;
import id.kostalk.app.R2;
import id.kostalk.app.data.remote.model.Building;
import id.kostalk.app.ui.base.BaseActivity;
import id.kostalk.app.ui.building_form.BuildingFormActivity;

public class BuildingActivity extends BaseActivity implements BuildingMvpView {

    @Inject
    BuildingMvpPresenter<BuildingMvpView> mPresenter;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.recycler_building)
    RecyclerView mRecyclerBuilding;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, BuildingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onGetBuildingList();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void buildBuildingList(List<Building> buildingList) {
        mRecyclerBuilding.setLayoutManager(new LinearLayoutManager(this));
        BuildingAdapter adapter = new BuildingAdapter(this, R.layout.item_building, buildingList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Building building = (Building) adapter.getData().get(position);
                startActivity(BuildingFormActivity.getStartIntent(BuildingActivity.this, building));
            }
        });
        mRecyclerBuilding.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R2.id.button_add_building)
    void onAddBuildingClick() {
        startActivity(BuildingFormActivity.getStartIntent(this));
    }
}
