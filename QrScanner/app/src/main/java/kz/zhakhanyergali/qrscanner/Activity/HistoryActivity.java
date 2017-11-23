package kz.zhakhanyergali.qrscanner.Activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kz.zhakhanyergali.qrscanner.Adapter.HistoryAdapter;
import kz.zhakhanyergali.qrscanner.Entity.History;
import kz.zhakhanyergali.qrscanner.R;
import kz.zhakhanyergali.qrscanner.SQLite.ORM.HistoryORM;

public class HistoryActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    // Init ui elements
    @BindView(R.id.historySwipeRefreshLayout)
    SwipeRefreshLayout historySwipeRefreshLayout;
    @BindView(R.id.historyRecyclerView)
    RecyclerView historyRecyclerView;

    // Variables
    HistoryORM h = new HistoryORM();
    List<History> historyList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        historySwipeRefreshLayout.setOnRefreshListener(this);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        historyRecyclerView.setLayoutManager(layoutManager);
        getData();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Отменяем анимацию обновления
                getData();
                historySwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    private void getData() {
        historyList = h.getAll(getApplicationContext());
        adapter = new HistoryAdapter(historyList);
        historyRecyclerView.setAdapter(adapter);
    }

    @OnClick
    void historyOnClickEvents(View v) {

        switch (v.getId()) {
            case R.id.backButton:
                finish();
                break;
            case R.id.clearButton:
                h.clearAll(getApplicationContext());
                Toast.makeText(getApplicationContext(), "The history is cleared, please refresh this page!", Toast.LENGTH_LONG).show();
                break;
        }
    }

}
