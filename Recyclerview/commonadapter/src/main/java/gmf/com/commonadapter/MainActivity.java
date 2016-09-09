package gmf.com.commonadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initView();
        initData();
    }

    private void init() {


    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.id_lv_main);
    }

    private void initData() {
        ArrayList<String> datas = new ArrayList<>(Arrays.asList("hello", "word", "city", "hello", "word", "city", "hello", "word", "city", "hello", "word", "city", "hello", "word", "city"));
        //        MyAdapter adapter = new MyAdapter(this, datas);
        //        mListView.setAdapter(adapter);
        mListView.setAdapter(new CommonAdapter<String>(this, datas, R.layout.cell_item) {
            @Override
            public void convert(ViewHolder holder, String item) {
//                TextView textView = holder.getView(R.id.id_tv_title);
//                textView.setText(item);
//                holder.setText(R.id.id_tv_title,item);
                holder.setText(R.id.tv_title, item);
                holder.setText(R.id.tv_describe, item);
                holder.setText(R.id.tv_phone, item);
                holder.setText(R.id.tv_time, item);
            }
        });
    }
}
