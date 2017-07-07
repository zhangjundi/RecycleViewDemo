package demo.zjd.com.recycleviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private List<SwipeCardBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         rv= (RecyclerView) findViewById(R.id.rv);
        RecyclerAdapter adapter=new RecyclerAdapter(this);
//        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rv.setAdapter(adapter);
//        rv.setLayoutManager(new HomeLayoutManager());
        rv.setLayoutManager(new SwipeCardLayoutManager());
        mData = SwipeCardBean.initDatas();
        SwipeCardCallback callback=new SwipeCardCallback(0,0,adapter,mData,rv);
        ItemTouchHelper helper=new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rv);
//        rv.addItemDecoration(new HistoryItemDecoration(this));
//        rv.addItemDecoration(new HomeItemDecoration());
    }
}
