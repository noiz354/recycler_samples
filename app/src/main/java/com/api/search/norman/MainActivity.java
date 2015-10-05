package com.api.search.norman;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
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
        Object[] rets = null;
        try {
            rets = JsonUtils.getJsonDataFromAssets(this);
            Log.d(Constant.LOG, rets.length+"");

            // gunakan rets[1] sebagai data
        } catch (IOException e) {
            e.printStackTrace();
        }

        mFlexibleSpaceOffset = getResources().getDimensionPixelSize(R.dimen.header_height);

        mHeader = findViewById(R.id.view_header);
        View paddingView = new View(this);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, mFlexibleSpaceOffset
        );
        paddingView.setLayoutParams(params);
        paddingView.setBackgroundColor(Color.WHITE);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new InnerDataAdapter((Data)rets[DATA]);
        ((InnerDataAdapter)mAdapter).addHeaderView(paddingView);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    boolean isIdle;
                    int mScrollY;

                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        isIdle = newState == RecyclerView.SCROLL_STATE_IDLE;
                        if (isIdle) {
                            mScrollY = 0;
                        }
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
                    }
                }
        );
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
