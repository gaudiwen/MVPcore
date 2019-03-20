package duofriend.com.paperplane.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import duofriend.com.paperplane.R;
import duofriend.com.paperplane.utils.commonutil.ConvertUtils;

public class MaxHeightRecyclerView extends RecyclerView {
	private int max_height = 200;
	private int item_height = 35;

	public MaxHeightRecyclerView(Context context) {
		super(context);
	}

	public MaxHeightRecyclerView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);

		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MaxHeightRecyclerView);
		int setMaxHeight = ta.getDimensionPixelSize(R.styleable.MaxHeightRecyclerView_max_height,0);
		int itemHeight = ta.getDimensionPixelSize(R.styleable.MaxHeightRecyclerView_item_height,0);
		if(setMaxHeight != 0){
			max_height = ConvertUtils.dp2px(setMaxHeight);
		}else {
			max_height = ConvertUtils.dp2px(max_height);
		}
		if(itemHeight != 0){
			item_height = ConvertUtils.dp2px(itemHeight);
		}else {
			item_height = ConvertUtils.dp2px(item_height);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (getAdapter() != null && getAdapter().getItemCount() > 0) {
			int height;
//			View child = getChildAt(0);
//			LayoutParams params = (LayoutParams) getLayoutParams();
//			child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			int max = getAdapter().getItemCount() * (item_height + getPaddingTop() + getPaddingBottom() );
			height = Math.min(max, max_height);
			setMeasuredDimension(widthMeasureSpec, height);
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}
}
