package gmf.com.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    //    private GalleryRecyclerView mRecyclerView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //        initView();
        initView();
        //        initData();
        initData();
    }

    //    private void initView() {
    //        //        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    //        mImageView = (ImageView) findViewById(R.id.id_content);
    //        mRecyclerView = (GalleryRecyclerView) findViewById(R.id.recyclerview);
    //        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    //    }
    private void initView() {
        //        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        //        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {

        List<String> datas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            datas.add("" + (char) i);
        }
        final HomeAdapter adapter = new HomeAdapter(this, datas);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
                adapter.addData(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "onItemLongClick" + position, Toast.LENGTH_SHORT).show();
                adapter.removeData(position);
            }
        });
    }

    private static class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

        private Context mContext;
        private List<String> mDatas;
        private OnItemClickListener mListener;

        public interface OnItemClickListener {

            void onItemClick(View view, int position);

            void onItemLongClick(View view, int position);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            mListener = listener;
        }

        public HomeAdapter(Context context, List<String> datas) {
            mContext = context;
            mDatas = datas;
        }

        @Override
        public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.cell_recycler_view_main, parent, false);
            return new HomeViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final HomeViewHolder holder, final int position) {
            holder.mTv.setText(mDatas.get(position));
            if (mListener != null) {

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getLayoutPosition();
                        mListener.onItemClick(holder.itemView, pos);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        if (mListener != null) {
                            int pos = holder.getLayoutPosition();
                            mListener.onItemLongClick(holder.itemView, pos);
                        }
                        return false;
                    }
                });
            }

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        public void addData(int position) {
            mDatas.add(position, "addOne");
            notifyItemInserted(position);
        }

        public void removeData(int position) {
            mDatas.remove(position);
            notifyItemRemoved(position);
        }

        class HomeViewHolder extends RecyclerView.ViewHolder {

            private TextView mTv;

            public HomeViewHolder(View itemView) {
                super(itemView);
                mTv = (TextView) itemView.findViewById(R.id.id_num);
            }
        }
    }

    //    private void initData() {
    //        final ArrayList<Integer> datas = new ArrayList<>(Arrays.asList(
    //                R.mipmap.ic_launcher,
    //                R.mipmap.ic_bind_card_mark,
    //                R.mipmap.ic_launcher,
    //                R.mipmap.ic_bind_card_mark,
    //                R.mipmap.ic_launcher,
    //                R.mipmap.ic_bind_card_mark,
    //                R.mipmap.ic_launcher,
    //                R.mipmap.ic_bind_card_mark,
    //                R.mipmap.ic_launcher,
    //                R.mipmap.ic_bind_card_mark,
    //                R.mipmap.ic_launcher,
    //                R.mipmap.ic_bind_card_mark));
    //        GalleryAdapter adapter = new GalleryAdapter(this, datas);
    //        adapter.setOnItemClickListener(new GalleryAdapter.OnItemClickListener() {
    //            @Override
    //            public void onItemClick(Context context, int position) {
    //                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
    //                mImageView.setImageResource(datas.get(position));
    //            }
    //        });
    //        mRecyclerView.setAdapter(adapter);
    //
    //        mRecyclerView.setOnItemScrollChangeListener(new GalleryRecyclerView.OnItemScrollChangeListener() {
    //            @Override
    //            public void onChange(View view, int position) {
    //                mImageView.setImageResource(datas.get(position));
    //            }
    //        });
    //    }


    //    private static class ViewHolder extends RecyclerView.ViewHolder {
    //        private final Context mContext;
    //        private final View mConvertView;
    //        private final SparseArray<View> mViews;
    //
    //        //        public ViewHolder(View itemView) {
    //        //            super(itemView);
    //        //        }
    //
    //        public ViewHolder(Context context, View itemView, ViewGroup parent) {
    //            super(itemView);
    //            mContext = context;
    //            mConvertView = itemView;
    //            mViews = new SparseArray<>();
    //        }
    //
    //        public static ViewHolder get(Context context, ViewGroup parent, int layoutId) {
    //            View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
    //            return new ViewHolder(context, itemView, parent);
    //        }
    //    }

    private static class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

        private Context mContext;
        private List<Integer> mDatas;
        private OnItemClickListener mListener;

        public interface OnItemClickListener {
            void onItemClick(Context context, int position);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            mListener = listener;
        }

        public GalleryAdapter(Context context, List<Integer> datas) {
            mContext = context;
            mDatas = datas;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.cell_recycler_view, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            Integer data = mDatas.get(position);
            holder.configCell(data, position);
            if (mListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onItemClick(mContext, position);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView mIv;
            TextView mTv;

            public ViewHolder(View itemView) {
                super(itemView);
                mIv = (ImageView) itemView.findViewById(R.id.id_index_gallery_item_image);
                mTv = (TextView) itemView.findViewById(R.id.id_index_gallery_item_text);
            }

            public void configCell(Integer data, int position) {
                mIv.setImageResource(data);
                mTv.setText("" + position);
            }
        }
    }


}
