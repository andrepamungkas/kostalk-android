package id.kostalk.app.ui.member;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import id.kostalk.app.R;
import id.kostalk.app.utils.CommonUtils;

public class MemberListAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public MemberListAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.TYPE_HEADER, R.layout.item_header);
        addItemType(MultipleItem.TYPE_ITEM, R.layout.item_member);
    }

//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//        FullSpanUtil.onAttachedToRecyclerView(recyclerView, this, MultipleItem.TYPE_HEADER);
//    }
//
//    @Override
//    public void onViewAttachedToWindow(BaseViewHolder holder) {
//        super.onViewAttachedToWindow(holder);
//        FullSpanUtil.onViewAttachedToWindow(holder, this, MultipleItem.TYPE_HEADER);
//    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TYPE_HEADER:
                helper.setText(R.id.text_section, item.getHeader());
                break;
            case MultipleItem.TYPE_ITEM:
                helper.setText(R.id.text_name, item.getAnggota().getNama())
                        .setText(R.id.text_cost, CommonUtils.rupiahFormat(item.getAnggota().getTagihan().getCost()));
                break;
        }

    }
}
