package demo.zjd.com.recycleviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zhangjd on 2017/7/3.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {

    private Context mContext;


    public RecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        CardConfig.initConfig(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false);
                holder = new ViewHolderOne(view);

                break;
            case 1:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_text, parent, false);
                holder = new ViewHolderTwo(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                ((ViewHolderOne) holder).iv.setBackgroundResource(R.mipmap.ceshi);
                break;
            case 1:
                ((ViewHolderTwo) holder).tv.setText("ceshi");
                ((ViewHolderTwo) holder).tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "测试", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

        }
    }

    @Override
    public int getItemViewType(int position) {
//        return position%2==0?0:1;
        return 0;
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        ImageView iv;

        public ViewHolderOne(View view) {
            super(view);
            iv = (ImageView) view.findViewById(R.id.iv);
        }
    }

    class ViewHolderTwo extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolderTwo(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv);
        }
    }
}
