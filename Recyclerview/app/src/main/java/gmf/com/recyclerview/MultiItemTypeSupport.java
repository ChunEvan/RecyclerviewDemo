package gmf.com.recyclerview;

/**
 * Created by Evan on 16/9/7 下午5:19.
 */
public interface MultiItemTypeSupport<T> {

    int getLayoutId(int viewType);

    int getItemViewType(int position, T t);
}
