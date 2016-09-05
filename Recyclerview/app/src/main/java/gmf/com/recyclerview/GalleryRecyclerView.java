package gmf.com.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Evan on 16/9/5 下午2:25.
 */
public class GalleryRecyclerView extends RecyclerView{

    private OnItemScrollChangeListener mListener;
    private View mCurrentView;

    public void setOnItemScrollChangeListener(OnItemScrollChangeListener listener) {
        mListener = listener;
    }

    public interface OnItemScrollChangeListener {
        void onChange(View view, int position);
    }

    public GalleryRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mCurrentView = getChildAt(0);
        if (mListener != null) {
            mListener.onChange(mCurrentView, getChildAdapterPosition(mCurrentView));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            mCurrentView = getChildAt(0);
            if (mListener != null) {
                mListener.onChange(mCurrentView, getChildAdapterPosition(mCurrentView));
            }
        }
        return super.onTouchEvent(e);
    }
}
