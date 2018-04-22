package com.ramesh.recyclerviewwithleftandrightarrows.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.ramesh.recyclerviewwithleftandrightarrows.ItemOffsetDecoration;
import com.ramesh.recyclerviewwithleftandrightarrows.R;
import com.ramesh.recyclerviewwithleftandrightarrows.StartSnapHelper;

import static android.support.v7.widget.RecyclerView.HORIZONTAL;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class RecyclerViewWithNavigationArrows extends RelativeLayout implements View.OnClickListener {

    RecyclerView recyclerView;
    AppCompatImageView leftArrow;
    AppCompatImageView rightArrow;

    //To track the position of the current visible item .for navigation with arrows
    int currentVisibleItem = 0;
    //To check whether user scrolled the recycler view or used arrows to navigate.
    private boolean programaticallyScrolled;
    ProgressBar progressBar;
    //LinearLayoutManagerWithSmoothScroller linearLayoutManagerWithSmoothScroller;
    private LinearLayoutManager linearLayoutManager;

    public RecyclerViewWithNavigationArrows(Context context) {
        super(context);
        init();
    }

    public RecyclerViewWithNavigationArrows(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecyclerViewWithNavigationArrows(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.recyclerview_with_arrows, this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        leftArrow = (AppCompatImageView) findViewById(R.id.tv_left_arrow);
        rightArrow = (AppCompatImageView) findViewById(R.id.tv_right_arrow);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.recyclerview_item_offset);
        recyclerView.addItemDecoration(itemDecoration);
        leftArrow.setOnClickListener(this);
        rightArrow.setOnClickListener(this);
        SnapHelper snapHelper = new StartSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case SCROLL_STATE_DRAGGING:
                        //Indicated that user scrolled.
                        programaticallyScrolled = false;
                        break;
                    case SCROLL_STATE_IDLE:
                        if (!programaticallyScrolled) {
                            currentVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                            handleWritingViewNavigationArrows(false);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * Handles the arrows visibility based on current visible items and scrolls the
     * current visibility item based on param scroll.
     *
     * Scroll - is False if User scrolls the Reycler Manually
     *        - is True means User used arrows to navigate
     *
     * @param scroll
     */
    private void handleWritingViewNavigationArrows(boolean scroll) {
        if (currentVisibleItem == (recyclerView.getAdapter().getItemCount() - 1)) {
            rightArrow.setVisibility(View.GONE);
            leftArrow.setVisibility(View.VISIBLE);
        } else if (currentVisibleItem != 0) {
            rightArrow.setVisibility(View.VISIBLE);
            leftArrow.setVisibility(View.VISIBLE);
        } else if (currentVisibleItem == 0) {
            leftArrow.setVisibility(View.GONE);
            rightArrow.setVisibility(View.VISIBLE);
        }
        if (scroll) {
            recyclerView.smoothScrollToPosition(currentVisibleItem);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_left_arrow:
                programaticallyScrolled = true;
                //Decrement current visible item position to navigate back to previous item
                currentVisibleItem--;
                handleWritingViewNavigationArrows(true);
                break;
            case R.id.tv_right_arrow:
                programaticallyScrolled = true;
                //Increment current visible item position to navigate next item
                currentVisibleItem++;
                handleWritingViewNavigationArrows(true);
                break;
            default:
                break;
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    public void showProgressBar() {
        progressBar.setVisibility(VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(GONE);
    }
}
