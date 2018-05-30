package id.kostalk.app.ui.history;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.kostalk.app.R;
import id.kostalk.app.R2;
import id.kostalk.app.data.remote.model.History;
import id.kostalk.app.ui.base.BaseFragment;

public class HistoryFragment extends BaseFragment implements HistoryMvpView {

    private static final String ARGS_BUILDING_ID = "id.ekos.app.ui.history.HistoryFragment.ARGS_BUILDING_ID";

    @Inject
    HistoryMvpPresenter<HistoryMvpView> mPresenter;

    @BindView(R2.id.recycler_history)
    RecyclerView mRecyclerHistory;

    long mBuildingId;

    public static HistoryFragment newInstance(long buildingId) {
        Bundle args = new Bundle();
        args.putLong(ARGS_BUILDING_ID, buildingId);
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
        setUp(view);
        return view;
    }

    @Override
    protected void setUp(View view) {
        mBuildingId = getArguments().getLong(ARGS_BUILDING_ID);
//        mPresenter.onGetHistoryList(mBuildingId);
    }

    @Override
    public void buildHistoryList(List<History> historyList) {
        mRecyclerHistory.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        List<HistoryMultipleItem> data = new ArrayList<>();
        int first = 0;
        for (History h : historyList) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            try {
                Date endDate = format.parse(h.getCreatedAt());
                long endMilisecond = new Date().getTime() - endDate.getTime();
                long oneDay = 86400000;
                long threeDay = 259200000;
                long sevenDay = 604800000;
                Long oneMonth = 2592000000L;
                if (endMilisecond < oneDay && first <= 1) {
                    data.add(new HistoryMultipleItem(HistoryMultipleItem.TYPE_HEADER, "Hari ini"));
                    first = 1;
                } else if (endMilisecond < threeDay && first <= 2) {
                    data.add(new HistoryMultipleItem(HistoryMultipleItem.TYPE_HEADER, "3 hari kemarin"));
                    first = 2;
                } else if (endMilisecond < sevenDay && first <= 3) {
                    data.add(new HistoryMultipleItem(HistoryMultipleItem.TYPE_HEADER, "1 minggu kemarin"));
                    first = 3;
                } else if (endMilisecond < oneMonth && first <= 4) {
                    data.add(new HistoryMultipleItem(HistoryMultipleItem.TYPE_HEADER, "1 bulan kemarin"));
                    first = 4;
                } else if (first <= 5) {
                    data.add(new HistoryMultipleItem(HistoryMultipleItem.TYPE_HEADER, "Lebih dari 1 bulan kemarin"));
                    first = 6;
                }

                if (h.getFlow().equals("in")) {
                    data.add(new HistoryMultipleItem(HistoryMultipleItem.TYPE_HISTORY_IN, h));
                } else {
                    data.add(new HistoryMultipleItem(HistoryMultipleItem.TYPE_HISTORY_OUT, h));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        HistoryListAdapter historyListAdapter = new HistoryListAdapter(data);
        historyListAdapter.openLoadAnimation();
        historyListAdapter.isFirstOnly(false);
        mRecyclerHistory.setAdapter(historyListAdapter);
    }
}
