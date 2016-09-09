package gmf.com.commonadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Evan on 16/9/7 上午11:32.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected final Context mContext;
    protected final List<T> mDatas;
    private final int mLayoutId;

    public CommonAdapter(Context context, List<T> datas, int layoutId) {
        mContext = context;
        mDatas = datas;
        mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        if (mDatas != null)
            return mDatas.size();
        return 0;
    }

    @Override
    public T getItem(int position) {
        if (mDatas != null)
            return mDatas.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = getViewHolder(mContext, convertView, parent, mLayoutId, position);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }

    private ViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        return ViewHolder.get(context, convertView, parent, layoutId, position);
    }

    public abstract void convert(ViewHolder holder, T item);
}
