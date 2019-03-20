package duofriend.com.paperplane.view.list.adapter;


import android.support.v7.widget.RecyclerView;

/**
 * Description:
 * Created by Yzq 2018/3/13 0013.
 * Emali: 277202583@qq.com
 */

public interface OnItemLongClickListner<T> {
    boolean onItemLongClick(RecyclerView.ViewHolder holder, int position, T item);
}
