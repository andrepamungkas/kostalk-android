package id.kostalk.app.ui.history;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import id.kostalk.app.R;

public class HistoryListAdapter extends BaseMultiItemQuickAdapter<HistoryMultipleItem, BaseViewHolder> {

    public HistoryListAdapter(List<HistoryMultipleItem> data) {
        super(data);
        addItemType(HistoryMultipleItem.TYPE_HEADER, R.layout.item_header);
        addItemType(HistoryMultipleItem.TYPE_HISTORY_IN, R.layout.item_history_in);
        addItemType(HistoryMultipleItem.TYPE_HISTORY_OUT, R.layout.item_history_out);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryMultipleItem item) {
        switch (helper.getItemViewType()) {
            case HistoryMultipleItem.TYPE_HEADER:
                helper.setText(R.id.text_section, item.getHeader());
                break;
            case HistoryMultipleItem.TYPE_HISTORY_IN:
            case HistoryMultipleItem.TYPE_HISTORY_OUT:
                helper.setText(R.id.text_amount, item.getHistory().getAmount())
                        .setText(R.id.text_issuer, item.getHistory().getInformation())
                        .setText(R.id.text_date, item.getHistory().getCreatedAt());
                break;
        }

    }
}
