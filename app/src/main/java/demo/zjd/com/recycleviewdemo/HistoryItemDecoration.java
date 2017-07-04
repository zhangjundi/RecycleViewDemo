package demo.zjd.com.recycleviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjd on 2017/7/4.
 */

public class HistoryItemDecoration extends RecyclerView.ItemDecoration {
    public static final int[] ATRRS = new int[]{
            android.R.attr.listDivider
    };
    private Drawable mDivider;
    private Bitmap bitmap;
    private Context mContext;
    private int space = 18;
    private int space_21 = 21;
    private int space_81 = 81;
    private int space_60 = 60;
    private List<Rect> list = new ArrayList<>();

    public HistoryItemDecoration(Context context) {
        mContext = context;
        space = (int) mContext.getResources().getDimension(R.dimen.dimen_18);
        space_21 = (int) mContext.getResources().getDimension(R.dimen.dimen_21);
        space_81 = (int) mContext.getResources().getDimension(R.dimen.dimen_81);
        space_60 = (int) mContext.getResources().getDimension(R.dimen.dimen_60);
        final TypedArray ta = context.obtainStyledAttributes(ATRRS);
        this.mDivider = ta.getDrawable(0);
        ta.recycle();
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.alarm);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int width = parent.getWidth();
        mDivider.setBounds(width / 2 - 1, 0, width / 2 + 1, parent.getBottom());
        mDivider.draw(c);
        int left;
        int right;
        for (int i = 0; i < parent.getChildCount(); i++) {
            View childAt = parent.getChildAt(i);
            int height = childAt.getHeight() / 3 + childAt.getTop();
            int left1 = childAt.getLeft();
            if (left1 > width / 2) {
                left = width / 2;
                right = childAt.getRight();
            } else {
                left = left1;
                right = width / 2;
            }
            mDivider.setBounds(left, height, right, height + 1);
            mDivider.draw(c);
            //绘制表盘
            c.save();
            c.translate(width / 2, height);
            c.drawBitmap(bitmap, -bitmap.getWidth() / 2, -bitmap.getHeight() / 2, null);
            c.restore();
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        list.add(outRect);
        int position = parent.getChildLayoutPosition(view);
        if (position == 0) {
            outRect.set(space, space_21, space * 2, 0);
        } else if (position == 1) {
            outRect.set(space * 2, space_81, space, 0);
        } else if (position % 2 == 0) {
            outRect.set(space, space_60, space * 2, 0);
        } else if (position % 2 == 1) {
            outRect.set(space * 2, space_60, space, 0);
        }
    }
}
