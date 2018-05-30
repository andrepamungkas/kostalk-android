package id.kostalk.app.ui.building;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import id.kostalk.app.R;
import id.kostalk.app.data.remote.model.Building;

public class BuildingAdapter extends BaseQuickAdapter<Building, BaseViewHolder> {

    private Context context;

    public BuildingAdapter(Context context, int layoutResId, List<Building> items) {
        super(layoutResId, items);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, Building item) {
        viewHolder.setText(R.id.text_name, item.getName())
                .setText(R.id.text_address, item.getAddress());
    }
}
