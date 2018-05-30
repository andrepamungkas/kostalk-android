package id.kostalk.app.ui.member;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.kostalk.app.R;
import id.kostalk.app.R2;
import id.kostalk.app.data.remote.model.Anggota;
import id.kostalk.app.data.remote.model.Building;
import id.kostalk.app.ui.base.BaseFragment;
import id.kostalk.app.ui.member_profile.MemberProfileActivity;

public class MemberFragment extends BaseFragment implements MemberMvpView {

    private static final String ARGS_BUILDING_ID = "id.ekos.app.ui.member.MemberFragment.ARGS_BUILDING_ID";
    private static final String ARGS_BUILDING = "id.ekos.app.ui.member.MemberFragment.ARGS_BUILDING";

    @Inject
    MemberMvpPresenter<MemberMvpView> mPresenter;

    @BindView(R2.id.recycler_member)
    RecyclerView mRecyclerMember;

//    long mBuildingId;
//    ArrayList<Building> mBuildingList;

    public static MemberFragment newInstance(long buildingId, ArrayList<Building> buildingList) {
        Bundle args = new Bundle();
        args.putLong(ARGS_BUILDING_ID, buildingId);
        args.putSerializable(ARGS_BUILDING, buildingList);
        MemberFragment fragment = new MemberFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member, container, false);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
        setUp(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onGetMemberList();
    }

    @Override
    protected void setUp(View view) {
//        mBuildingId = getArguments().getLong(ARGS_BUILDING_ID);
//        mBuildingList = (ArrayList<Building>) getArguments().getSerializable(ARGS_BUILDING);
//        mPresenter.onGetMemberList(mBuildingId);
//        List<Anggota> memberList = new ArrayList<>();

//        memberList.add(new Anggota("Andre Pamungkas", "081234567890", 500000, new Tagihan(500000, "2018-04-11", "2018-05-10")));
//        memberList.add(new Anggota("Riris Bayu Asrori", "081234567890", 550000, new Tagihan(550000, "2018-04-9", "2018-06-10")));
//        buildMemberList(memberList);
    }

    @Override
    public void buildMemberList(List<Anggota> anggotaList) {
        List<MultipleItem> data = new ArrayList<>();
//        int first = 0;
        for (Anggota m : anggotaList) {
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            try {
//                Date endDate = format.parse(m.getTagihan().getEnd());
//                long endMilisecond = endDate.getTime() - new Date().getTime();
//                long oneDay = 86400000;
//                long threeDay = 259200000;
//                long sevenDay = 604800000;
//                Long oneMonth = 2592000000L;
//                if (endMilisecond < 0 && first == 0) {
//                    data.add(new MultipleItem(MultipleItem.TYPE_HEADER, "Sudah lewat"));
//                    first = 1;
//                } else if (endMilisecond < oneDay && first <= 1) {
//                    data.add(new MultipleItem(MultipleItem.TYPE_HEADER, "Hari ini"));
//                    first = 2;
//                } else if (endMilisecond < threeDay && first <= 2) {
//                    data.add(new MultipleItem(MultipleItem.TYPE_HEADER, "3 Hari ini"));
//                    first = 3;
//                } else if (endMilisecond < sevenDay && first <= 3) {
//                    data.add(new MultipleItem(MultipleItem.TYPE_HEADER, "1 Minggu ini"));
//                    first = 4;
//                } else if (endMilisecond < oneMonth && first <= 4) {
//                    data.add(new MultipleItem(MultipleItem.TYPE_HEADER, "Bulan ini"));
//                    first = 5;
//                } else if (first <= 5) {
//                    data.add(new MultipleItem(MultipleItem.TYPE_HEADER, "Lebih dari 30 hari"));
//                    first = 6;
//                }
                data.add(new MultipleItem(MultipleItem.TYPE_ITEM, m));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
        }
        Log.d("jumlah anggota", String.valueOf(data.size()));

        mRecyclerMember.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        MemberListAdapter memberListAdapter = new MemberListAdapter(data);
        memberListAdapter.openLoadAnimation();
        memberListAdapter.isFirstOnly(false);
        memberListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultipleItem item = (MultipleItem) adapter.getData().get(position);
                if (item.getItemType() == MultipleItem.TYPE_ITEM) {
                    startActivity(MemberProfileActivity.getStartIntent(getBaseActivity(), 0, item.getAnggota()));
                }
            }
        });
        mRecyclerMember.setAdapter(memberListAdapter);
    }

    public void updateBuilding(long id) {
        mPresenter.onGetMemberList();
//        mBuildingId = id;
    }
}
