package gmf.com.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Evan on 16/9/7 下午4:52.
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private final Context mContext;
    private final int mLayoutId;
    private final List<T> mDatas;

    public CommonAdapter(Context context, int layoutId, List<T> datas) {
        mContext = context;
        mLayoutId = layoutId;
        mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.get(mContext,mLayoutId,parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder,mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public abstract void convert(ViewHolder holder,T item);
}
