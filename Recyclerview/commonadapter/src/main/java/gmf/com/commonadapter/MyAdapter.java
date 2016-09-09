package gmf.com.commonadapter;

import android.content.Context;

import java.util.List;

/**
 * Created by Evan on 16/9/7 上午9:56.
 */
public class MyAdapter extends CommonAdapter {

    public MyAdapter(Context context, List datas,int layoutId) {
        super(context, datas,layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Object item) {

    }

    //    @Override
//    public View getView(int position, View convertView, ViewGroup viewGroup) {
//        ViewHolder holder = ViewHolder.get(mContext, convertView, viewGroup, R.layout.cell_item, position);
//        TextView mTv = holder.getView(R.id.id_tv_title);
//        mTv.setText((String) mDatas.get(position));
//        return holder.getConvertView();
//    }

    //
    //    private final Context mContext;
    //    private final List<String> mDatas;
    //
    //    public MyAdapter(Context context, List<String> datas) {
    //        mContext = context;
    //        mDatas = datas;
    //    }
    //
    //    @Override
    //    public int getCount() {
    //        if (mDatas != null)
    //            return mDatas.size();
    //        return 0;
    //    }
    //
    //    @Override
    //    public Object getItem(int position) {
    //        if (mDatas != null) {
    //            return mDatas.get(position);
    //        }
    //        return null;
    //    }
    //
    //    @Override
    //    public long getItemId(int position) {
    //        return position;
    //    }
    //
    //    @Override
    //    public View getView(int position, View convertView, ViewGroup viewGroup) {
    //        ViewHolder holder = null;
    //        //        if (convertView == null) {
    //        //            convertView = LayoutInflater.from(mContext).inflate(R.layout.cell_item, viewGroup, false);
    //        //            holder = new ViewHolder();
    //        //            holder.mTv = (TextView) convertView.findViewById(R.id.id_tv_title);
    //        //            convertView.setTag(holder);
    //        //        } else {
    //        //            holder = (ViewHolder) convertView.getTag();
    //        //        }
    //        //        holder.mTv.setText(mDatas.get(position));
    //        holder = ViewHolder.get(mContext, convertView, viewGroup, R.layout.cell_item, position);
    //        TextView mTv = holder.getView(R.id.id_tv_title);
    //        mTv.setText(mDatas.get(position));
    //
    //        return holder.getConvertView();
    //    }
    //
    //    //    private class ViewHolder {
    //    //        TextView mTv;
    //    //    }
}
