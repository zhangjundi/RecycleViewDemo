package demo.zjd.com.recycleviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangjd on 2017/7/4.
 */

public class HomeLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
//        super.onLayoutChildren(recycler, state);
        //h缓存的核心机制之一
        detachAndScrapAttachedViews(recycler);

        int itemCount = getItemCount();
        int initPosition;
        if (itemCount < CardConfig.MAX_SHOW_COUNT) {
            initPosition = 0;
        } else {
            initPosition = itemCount - CardConfig.MAX_SHOW_COUNT;
        }

        int i=0;
        int left=0;
        for (int position = initPosition; position < itemCount; position++) {
            //从缓存获取view
            View view = recycler.getViewForPosition(position);
            addView(view);
            measureChild(view, 0, 0);

            int widthSpace = getWidth();
            int heightSpace = getHeight()-getDecoratedMeasuredHeight(view);
            layoutDecorated(view,
                    left,
                    heightSpace/2,
                    left+ getDecoratedMeasuredWidth(view),
                    heightSpace/2 + getDecoratedMeasuredHeight(view));
            // 均匀的缩放和位移
            view.setTag(android.R.id.button1,i);
            i++;

        }
    }
}
