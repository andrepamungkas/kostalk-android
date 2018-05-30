package id.kostalk.app.ui.building_form;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.kostalk.app.R;
import id.kostalk.app.R2;
import id.kostalk.app.data.remote.model.Building;
import id.kostalk.app.ui.base.BaseActivity;

public class BuildingFormActivity extends BaseActivity implements BuildingFormMvpView {

    private static final String EXTRA_BUILDING = "id.ekos.app.ui.building_form.BuildingFormActivity.EXTRA_BUILDING";

    @Inject
    BuildingFormMvpPresenter<BuildingFormMvpView> mPresenter;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.text_toolbar)
    TextView mTextToolbar;

    @BindView(R2.id.input_name)
    EditText mInputName;

    @BindView(R2.id.button_add)
    Button mButtonAdd;

    @BindView(R2.id.layout_detail)
    LinearLayout mLayoutDetailButton;

    Building mBuilding;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, BuildingFormActivity.class);
        return intent;
    }

    public static Intent getStartIntent(Context context, Building building) {
        Intent intent = new Intent(context, BuildingFormActivity.class);
        intent.putExtra(EXTRA_BUILDING, building);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_form);

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

        if (getIntent().hasExtra(EXTRA_BUILDING)) {
            mButtonAdd.setVisibility(View.GONE);
            mBuilding = (Building) getIntent().getSerializableExtra(EXTRA_BUILDING);
            mTextToolbar.setText(R.string.building);
            mInputName.setText(mBuilding.getName());
        } else {
            mLayoutDetailButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void reopenBuildingActivity() {
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R2.id.button_add)
    void onAddClick() {
        String name = mInputName.getText().toString();
        mPresenter.onAddClick(name);
    }

    @OnClick(R2.id.button_update)
    void onUpdateClick() {
        String name = mInputName.getText().toString();
        mPresenter.onUpdateClick(mBuilding.getId(), name);
    }

    @OnClick(R2.id.button_delete)
    void onDeleteClick() {
        mPresenter.onDeleteClick(mBuilding.getId());
    }
}
