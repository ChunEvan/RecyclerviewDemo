package gmf.com.recyclerview;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Evan on 16/9/7 下午5:23.
 */
public abstract class MultiItemCommonAdapter<T> extends CommonAdapter<T> {

    private final MultiItemTypeSupport<T> mMultiItemTypeSupport;
    private final List<T> mDatas;
    private final Context mContext;

    public MultiItemCommonAdapter(Context context, List<T> datas, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, -1, datas);
        mContext = context;
        mDatas = datas;
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        return ViewHolder.get(mContext, layoutId, parent);
    }
}
