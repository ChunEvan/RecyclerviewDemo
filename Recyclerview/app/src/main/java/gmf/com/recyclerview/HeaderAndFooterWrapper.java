package gmf.com.recyclerview;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Evan on 16/9/8 上午10:43.
 */
public class HeaderAndFooterWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BASE_ITEM_TYPE_HEADER = 10000;
    private static final int BASE_ITEM_TYPE_FOOTER = 20000;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFooterViews = new SparseArrayCompat<>();


    private final RecyclerView.Adapter mInnerAdapter;

    public HeaderAndFooterWrapper(RecyclerView.Adapter adapter) {
        mInnerAdapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {

        } else if (mFooterViews.get(viewType) != null) {

        }
        return mInnerAdapter.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderViewPos(position))
            return;
        if (isFooterViewPos(position))
            return;
        mInnerAdapter.onBindViewHolder(holder, position - getHeaderViewCount());
    }

    @Override
    public int getItemCount() {
        return mInnerAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterViewPos(position)) {
            return mFooterViews.keyAt(position - getHeaderViewCount() - getRealItemCount());
        }
        return mInnerAdapter.getItemViewType(position - getHeaderViewCount());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int itemViewType = getItemViewType(position);
                    if (mHeaderViews.get(itemViewType) != null) {
                        return ((GridLayoutManager) layoutManager).getSpanCount();
                    } else if (mFooterViews.get(itemViewType) != null) {
                        return ((GridLayoutManager) layoutManager).getSpanCount();
                    }

                    if (spanSizeLookup != null) {
                        return spanSizeLookup.getSpanSize(position);
                    }
                    return 1;
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeaderViewPos(position) || isFooterViewPos(position)){
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
            if (params!=null && params instanceof StaggeredGridLayoutManager.LayoutParams){
                StaggeredGridLayoutManager.LayoutParams lp= (StaggeredGridLayoutManager.LayoutParams) params;
                lp.setFullSpan(true);
            }
        }

    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeaderViewCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeaderViewCount() + getRealItemCount();
    }

    private void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    private void addFooterView(View view) {
        mFooterViews.put(mFooterViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    private int getHeaderViewCount() {
        return mHeaderViews.size();
    }

    private int getFooterViewCount() {
        return mFooterViews.size();
    }

    private int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }

}
