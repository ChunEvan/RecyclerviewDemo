package gmf.com.addheader;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Evan on 16/9/8 下午4:05.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private ArrayList<String> mDatas = new ArrayList<>();

    private OnItemClickListener mOnItemClickListener;
    private View mHeaderView;

    interface OnItemClickListener {
        void onClickListener(int position, String data);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyDataSetChanged();
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void setDatas(ArrayList<String> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER)
            return new ViewHolder(mHeaderView);
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_HEADER)
            return;
        int realPosition = getRealPosition(holder);
        final String data = mDatas.get(realPosition);
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).mTextView.setText(data);
            if (mOnItemClickListener == null)
                return;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onClickListener(position, data);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mDatas.size() : mDatas.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null)
            return TYPE_NORMAL;
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager manager = (GridLayoutManager) layoutManager;
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER ? manager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        if (params != null && params instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) params;
            lp.setFullSpan(holder.getLayoutPosition() == 0);
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int layoutPosition = holder.getLayoutPosition();
        return mHeaderView == null ? layoutPosition : layoutPosition - 1;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView)
                return;
            mTextView = (TextView) itemView.findViewById(R.id.label_text);
        }
    }
}
