package gmf.com.addheader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Evan on 16/9/8 下午5:17.
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mDivider;
    private boolean mHasHeader;

    public GridItemDecoration(Context context) {
        TypedArray ta = context.obtainStyledAttributes(ATTRS);
        if (ta != null) {
            mDivider = ta.getDrawable(0);
            ta.recycle();
        }
    }

    public GridItemDecoration(Context context, boolean header) {
        this(context);
        mHasHeader = header;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager manager = (GridLayoutManager) layoutManager;
            int spanCount = manager.getSpanCount();
            int position = parent.getChildAdapterPosition(view);
            int pos = position;
            if (mHasHeader) {
                if (position == 0) {
                    outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
                    return;
                } else {
                    pos = position - 1;
                }
            }
        }
    }
}
