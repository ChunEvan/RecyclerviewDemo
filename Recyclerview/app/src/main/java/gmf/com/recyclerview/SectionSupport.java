package gmf.com.recyclerview;

/**
 * Created by Evan on 16/9/7 下午5:50.
 */
public interface SectionSupport<T> {

    public int sectionHeaderLayoutId();

    public int sectionTitleTextViewId();

    public String getTitle(T t);

}
