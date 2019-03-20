package duofriend.com.paperplane.view.list.adapter;

/**
 * Description:
 * Created by Yzq 2018/3/13 0013.
 * Emali: 277202583@qq.com
 */

public interface OnItemClickListener<T>{
    void onItemClick(ViewHolder holder, int position, T item);
}
