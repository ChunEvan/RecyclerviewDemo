package gmf.com.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by Evan on 16/9/5 下午4:33.
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mDivier;

    public DividerGridItemDecoration(Context context) {
        TypedArray ta = context.obtainStyledAttributes(ATTRS);
        if (ta != null) {
            mDivier = ta.getDrawable(0);
            ta.recycle();
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getLeft() - params.leftMargin;
            int right = child.getRight() + params.rightMargin + mDivier.getIntrinsicWidth();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivier.getIntrinsicHeight();
            mDivier.setBounds(left, top, right, bottom);
            mDivier.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getTop() - params.topMargin;
            int bottom = child.getBottom() + params.bottomMargin;
            int left = child.getRight() + params.rightMargin;
            int right = left + mDivier.getIntrinsicWidth();
            mDivier.setBounds(left, top, right, bottom);
            mDivier.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        int spanCount = spanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (isLastColum(parent, pos, spanCount, childCount)) {
            outRect.set(0, 0, 0, mDivier.getIntrinsicHeight());
        } else if (isLastRow(parent, pos, spanCount, childCount)) {
            outRect.set(0, 0, mDivier.getIntrinsicWidth(), 0);
        } else {
            outRect.set(0, 0, mDivier.getIntrinsicWidth(), mDivier.getIntrinsicHeight());
        }
    }

    private int spanCount(RecyclerView parent) {
        int spanCount = -1;
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) manager).getSpanCount();
        } else if (manager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) manager).getSpanCount();
        }
        return spanCount;
    }


    private boolean isLastColum(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0) {
                return true;
            }
        } else if (manager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) manager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isLastRow(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount) {
                return true;
            }
        } else if (manager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) manager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)
                    return true;
            } else {
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }


}
