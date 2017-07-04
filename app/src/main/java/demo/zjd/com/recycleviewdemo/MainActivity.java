package demo.zjd.com.recycleviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         rv= (RecyclerView) findViewById(R.id.rv);
        RecyclerAdapter adapter=new RecyclerAdapter(this);
//        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rv.setAdapter(adapter);
        rv.setLayoutManager(new HomeLayoutManager());

//        rv.addItemDecoration(new HistoryItemDecoration(this));
    }
}
