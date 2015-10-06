package com.api.search.norman;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;

import com.api.search.norman.adapters.InnerDataAdapter;
import com.api.search.norman.models.Data;
import com.api.search.norman.utils.Constant;
import com.api.search.norman.utils.JsonUtils;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements Constant.JsonUtilsConstant{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int mFlexibleSpaceOffset;
    private boolean mHeaderIsShown;
    View mHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Object[] returnJson = null;
        try {
            returnJson = JsonUtils.getJsonDataFromAssets(this);
            Log.d(Constant.LOG, returnJson.length+"");

            // gunakan rets[1] sebagai data
        } catch (IOException e) {
            e.printStackTrace();
        }

        // translation value
        mFlexibleSpaceOffset = getResources().getDimensionPixelSize(R.dimen.header_height);

        mHeader = findViewById(R.id.view_header);
        View paddingView = setupHeaderView();
        setupRecyclerView(returnJson);

        ((InnerDataAdapter)mAdapter).addHeaderView(paddingView);
    }

    @NonNull
    private View setupHeaderView() {
        View paddingView = new View(this);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, mFlexibleSpaceOffset
        );
        paddingView.setLayoutParams(params);
        paddingView.setBackgroundColor(Color.WHITE);
        return paddingView;
    }

    private void setupRecyclerView(Object[] returnJson) {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
//        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        final Data data = (Data) returnJson[DATA];
        Data newData = new Data();
        List<Data.InnerData> firstHalf = new ArrayList<>();
        for(int i=0;i<data.getDatas().size();i++){
            firstHalf.add(data.getDatas().get(i));
        }
        newData.setDatas(firstHalf);
        mAdapter = new InnerDataAdapter(newData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnScrollListener(
                new EndlessRecyclerOnScrollListener((LinearLayoutManager)mLayoutManager) {
                    @Override
                    public void onLoadMore(int current_page) {
                        Log.d("MNORMANSYAH", "masuk load morenya");
                        List<Data.InnerData> lastHalf = new ArrayList<>();
                        for(int i=data.getDatas().size()/2;i<data.getDatas().size();i++){
                            lastHalf.add(data.getDatas().get(i));
                        }
                        Data newData = new Data(lastHalf);
                        ((InnerDataAdapter)mAdapter).addNewData(newData);
                    }
                }
        );
    }

    abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener{
//        public static final String TAG = EndlessRecyclerOnScrollListener.class.getSimpleName();

        private int previousTotal = 0; // The total number of items in the dataset after the last load
        private boolean loading = true; // True if we are still waiting for the last set of data to load.
        private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
        int firstVisibleItem, visibleItemCount, totalItemCount;

        private int current_page = 1;

        private LinearLayoutManager mLinearLayoutManager;

        boolean isIdle;
        int mScrollY;

        public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager) {
            this.mLinearLayoutManager = linearLayoutManager;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            mScrollY += dy;
            // show or hide header view
            if (mScrollY > 12) {
                hideHeader();
            } else {
                showHeader();
            }

            visibleItemCount = recyclerView.getChildCount();
            totalItemCount = mLinearLayoutManager.getItemCount();
            firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal = totalItemCount;
                }
            }
            if (!loading && (totalItemCount - visibleItemCount)
                    <= (firstVisibleItem + visibleThreshold)) {
                // End has been reached

                // Do something
                current_page++;

                onLoadMore(current_page);

                loading = true;
            }
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            isIdle = newState == RecyclerView.SCROLL_STATE_IDLE;
            if (isIdle) {
                mScrollY = 0;
            }
        }

        public abstract void onLoadMore(int current_page);
    }

    private void showHeader() {
        if (!mHeaderIsShown) {
            ViewPropertyAnimator.animate(mHeader).cancel();
            ViewPropertyAnimator.animate(mHeader).translationY(0).setDuration(200).start();
            mHeaderIsShown = true;
        }
    }

    private void hideHeader() {
        if (mHeaderIsShown) {
            ViewPropertyAnimator.animate(mHeader).cancel();
            ViewPropertyAnimator.animate(mHeader).translationY(-mFlexibleSpaceOffset).setDuration(200).start();
            mHeaderIsShown = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
