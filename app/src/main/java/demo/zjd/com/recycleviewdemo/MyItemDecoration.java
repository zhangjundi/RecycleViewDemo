package demo.zjd.com.recycleviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhangjd on 2017/7/4.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {


    //我们通过获取系统属性中的listDivider来添加，在系统中的AppTheme中设置
    public static final int[] ATRRS = new int[]{
            android.R.attr.listDivider
    };
    private Drawable mDivider;
    private Paint paint;


    public MyItemDecoration(Context context) {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        final TypedArray ta = context.obtainStyledAttributes(ATRRS);
        this.mDivider = ta.getDrawable(0);
        ta.recycle();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, mDivider.getIntrinsicHeight(), mDivider.getIntrinsicHeight());
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int left;
        int top;
        int right;
        int bottom;
        for (int i = 0; i < childCount; i++) {
            View childAt = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            left = childAt.getLeft() - params.leftMargin;
            top = childAt.getTop() - params.topMargin;
            right = childAt.getRight() + params.rightMargin + mDivider.getIntrinsicHeight();
            bottom = childAt.getBottom() + params.bottomMargin + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
