package demo.zjd.com.recycleviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangjd on 2017/7/4.
 */

public class HomeLayoutManager extends RecyclerView.LayoutManager {
    private int startPositon;//起始位置
    private int endPositon;//结束位置
    private int scorllX;
    private int childWidth=0;
    private float startDegrees=60;
    private float endDegrees=-60;


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
        if(startPositon>itemCount-1){
            startPositon=itemCount-CardConfig.MAX_SHOW_COUNT;
        }
        if(startPositon<0){
            startPositon=0;
        }

        endPositon=Math.min(startPositon+CardConfig.MAX_SHOW_COUNT,itemCount);
        int i = 0;
        int left = getPaddingLeft();
        for (int position = startPositon; position < endPositon; position++) {
            //从缓存获取view
            View view = recycler.getViewForPosition(position);
            if(childWidth==0){
                childWidth=getDecoratedMeasuredWidth(view);
            }
            addView(view);
            view.setScaleX(1);
            view.setScaleY(1);
            int widthSpace = getWidth();
            int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);
            measureChildWithMargins(view, RecyclerView.LayoutParams.WRAP_CONTENT,RecyclerView.LayoutParams.WRAP_CONTENT);
//            view.measure(widthSpace / 4, heightSpace);
            layoutDecorated(view,
                    left+scorllX,
                    heightSpace / 2,
                    left + childWidth+scorllX,
                    heightSpace / 2 + getDecoratedMeasuredHeight(view));
            // 均匀的缩放和位移
//            view.setTag(R.id.iv, i);
////            view.getWidth()*view.getWidth()-width*width/16
//            if (i == 3) {
//                view.setRotationY(endDegrees);
//            } else if (i == 0)
//                view.setRotationY(startDegrees);
//            else {
////                view.set
//                view.setScaleX(0.75f);
//                view.setScaleY(0.75f);
//                if(i==1)
//                    view.setTranslationX(-20f+scorllX);
//                else
//                    view.setTranslationX(20f+scorllX);
//            }
            left += childWidth;
//            i++;
        }
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        scorllX-=dx;


        if(scorllX>childWidth){
            scorllX=0;
            startDegrees=60;
            endDegrees=-60;
            startPositon-=1;
        }else if(scorllX<-childWidth){
            scorllX=0;
            startDegrees=60;
            endDegrees=-60;
            startPositon+=1;
        }
//        if(scorllX<0){
//            //            childWidth
//            double sin = Math.sin((width + scorllX)*1.00 / childWidth);
//            double degrees = Math.toDegrees(sin);
//            startDegrees= (float) (90-degrees);
//        }else if(scorllX>0){
//            double sin = Math.sin((width + scorllX)*1.00 / childWidth);
//            double degrees = Math.toDegrees(sin);
//            endDegrees= (float) (90+degrees);
//        }

        onLayoutChildren(recycler,state);
        return super.scrollHorizontallyBy(dx, recycler, state);
    }
}
