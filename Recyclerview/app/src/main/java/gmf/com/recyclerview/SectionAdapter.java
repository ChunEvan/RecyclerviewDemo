package gmf.com.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Evan on 16/9/7 下午5:54.
 */
public abstract class SectionAdapter extends MultiItemCommonAdapter {

    private static final int TYPE_SECTION = 0;
    private final Context mContext;
    private final List mDatas;
    private final SectionSupport mSectionSupport;
    private final int mLayoutId;
    private final MultiItemTypeSupport mMultiItemTypeSupport;


    MultiItemTypeSupport headerItemTypeSupport = new MultiItemTypeSupport() {
        @Override
        public int getLayoutId(int viewType) {
            if (viewType == TYPE_SECTION) {
                return mSectionSupport.sectionHeaderLayoutId();
            }
            return mLayoutId;
        }

        @Override
        public int getItemViewType(int position, Object o) {
            return mSections.values().contains(position) ? TYPE_SECTION : 1;
        }
    };

    RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {

        @Override
        public void onChanged() {
            super.onChanged();
            findSections();
        }
    };

    protected void findSections() {
        int n = mDatas.size();
        int nSections = 0;
        mSections.clear();
        for (int i = 0; i < n; i++) {
            String title = mSectionSupport.getTitle(mDatas.get(i));
            if (!mSections.containsKey(title)) {
                mSections.put(title, 1 + nSections);
                nSections++;
            }
        }
    }


    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        unregisterAdapterDataObserver(observer);
    }

    private final LinkedHashMap<String, Integer> mSections;

    public SectionAdapter(Context context, int layoutId, List datas, SectionSupport sectionSupport) {
        super(context, datas, null);
        mSections = new LinkedHashMap<>();
        mMultiItemTypeSupport = headerItemTypeSupport;
        mLayoutId = layoutId;
        mContext = context;
        mDatas = datas;
        mSectionSupport = sectionSupport;
        findSections();
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, null);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + mSections.size();
    }

    public int getIndexForPosition(int position) {
        int nSection = 0;
        Set<Map.Entry<String, Integer>> entrySet = mSections.entrySet();

        for (Map.Entry<String, Integer> entry : entrySet) {
            if (entry.getValue() < position) {
                nSection++;
            }
        }
        return position - nSection;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        position = getIndexForPosition(position);
        if (holder.getItemViewType() == TYPE_SECTION) {
            holder.setText(mSectionSupport.sectionTitleTextViewId(),mSectionSupport.getTitle(mDatas.get(position)));
            return;
        }
        super.onBindViewHolder(holder, position);

    }
}
