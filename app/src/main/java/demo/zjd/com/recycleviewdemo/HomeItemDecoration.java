package demo.zjd.com.recycleviewdemo;

import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/7/4/004.
 */

public class HomeItemDecoration extends RecyclerView.ItemDecoration {

    private  Matrix mMatrix;
    private  Camera mCamera;

    public HomeItemDecoration(){
        mCamera=new Camera();
        mMatrix=new Matrix();
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int width = parent.getWidth();
        for(int i=0;i<childCount;i++){
            View view = parent.getChildAt(i);
            mCamera.save();
            switch (i){
                case 0:
                    float z=(float) Math.sqrt(view.getWidth()*view.getWidth()-width*width/16);
                    mCamera.setLocation(0,0,z);
                    mCamera.getMatrix(mMatrix);
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
            c.concat(mMatrix);
            view.draw(c);
            mCamera.restore();
            mMatrix.reset();
        }
    }
}
