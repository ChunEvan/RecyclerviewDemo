package gmf.com.addheader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMyAdapter = new MyAdapter();
        ArrayList<String> datas = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        mMyAdapter.setDatas(datas);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mMyAdapter.getItemViewType(position) == MyAdapter.TYPE_HEADER ? gridLayoutManager.getSpanCount() : 1;
            }
        });


//        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        View headerView = LayoutInflater.from(this).inflate(R.layout.cell_item, mRecyclerView, false);
        TextView textView = (TextView) headerView.findViewById(R.id.label_text);
        textView.setText("Header");
        mMyAdapter.setHeaderView(headerView);
        mMyAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(int position, String data) {
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mMyAdapter);

    }
}
