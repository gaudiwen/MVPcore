package duofriend.com.paperplane.view.list.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import duofriend.com.paperplane.R;
import duofriend.com.paperplane.utils.GlideUtils;
import duofriend.com.paperplane.view.GlideApp;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Description:
 * Created by Yzq 2018/3/13 0013.
 * Email: 277202583@qq.com
 */

public class ViewHolder extends RecyclerView.ViewHolder{
    private final SparseArray<View> views;
    private View convertView;
    private Context context;
    private int position;

    protected ViewHolder(Context context, View convertView, ViewGroup viewGroup) {
        super(convertView);
        this.context = context;
        this.convertView = convertView;
        this.views = new SparseArray<>();

    }

    public static ViewHolder create(Context context, int layoutId, ViewGroup viewGroup){
         View view = LayoutInflater.from(context).inflate(layoutId, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(context, view, viewGroup);
        return viewHolder;
    }

    public static ViewHolder create(View view){
        return new ViewHolder(view.getContext(), view, null);
    }

    public Context getContext() {
        return context;
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return convertView;
    }

    public ViewHolder setText(int viewId, String text) {
            TextView textView = getView(viewId);
            textView.setText(text);
        return this;
    }

    public ViewHolder setText(int viewId, String text,boolean hasLine) {
        TextView textView = getView(viewId);
        if(hasLine){
            textView.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        }
        textView.setText(text);
        return this;
    }
    public ViewHolder setText(int viewId, int textId) {
        String text = context.getResources().getString(textId);
        setText(viewId, text);
        return this;
    }

    public ViewHolder setImage(int viewId,@DrawableRes int imageId) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), imageId);
        setImage(viewId, drawable);
        return this;
    }


    public ViewHolder setImage(int viewId, Drawable drawable) {
        ImageView imageView = getView(viewId);
        imageView.setImageDrawable(drawable);
        return this;
    }

    public ViewHolder setImage(int viewId, String url, Transformation transformation){
        final ImageView imageView = getView(viewId);
        Glide.with(getContext()).load(url).apply(GlideUtils.getInstance().noneCacheOpt()).into(imageView);
        return this;
    }

    public ViewHolder setCircleImage(int viewId, String url) {
        final ImageView imageView = getView(viewId);
        Glide.with(getContext())
                .load(url)
                .apply(GlideUtils.getInstance().getAllCacheOptions())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .thumbnail(0.5f)
                .into(imageView);
        return this;
    }

    public ViewHolder setImage(int viewId, String url) {
        final ImageView imageView = getView(viewId);
        Glide.with(getContext())
                .load(url)
                .apply(GlideUtils.getInstance().getAllCacheOptions())
                .into(imageView);
        return this;
    }

    /**
     *Time:2018/11/7
     *Author:Gaodi.
     *Description:设置先加载缩略图再加载原图
     */
    public ViewHolder setThumbnailImage(int viewId, String url) {
        final ImageView imageView = getView(viewId);
        Glide.with(context)
                .load(url)
                .thumbnail(0.1f)
                .apply(GlideUtils.getInstance().getAllCacheOptions())
                .into(imageView);
        return this;
    }

    /**
     *Time:2018/11/20
     *Author:Gaodi.
     *Description:预加载
     */
    public ViewHolder setPrepareImage(int viewId, String url) {
        final ImageView imageView = getView(viewId);
        GlideApp.with(context)
                .load(url)
                .placeholder(R.drawable.ic_prepare_load)
                .thumbnail(0.2f)
                .apply(GlideUtils.getInstance().getAllCacheOptions())
                .error(R.drawable.ic_fail_load)
                .into(imageView);
        return this;
    }

    public ViewHolder setImage(int viewId, String url,int radius) {
        final ImageView imageView = getView(viewId);
        Glide.with(context)
                .load(url)
                .apply(GlideUtils.getInstance().noneCacheOpt())
                .apply(bitmapTransform(new RoundedCornersTransformation(radius, 0,
                        RoundedCornersTransformation.CornerType.ALL)))
//                .apply(new RequestOptions().override(imageView.getWidth(),imageView.getHeight()))
                .transition(withCrossFade())
                .into(imageView);
        return this;
    }

    public ViewHolder setImage(int viewId, String url,int radius,@DrawableRes int id) {
        final ImageView imageView = getView(viewId);
        Glide.with(context)
                .load(url)
                .apply(GlideUtils.getInstance().getAllCacheOptions())
                .apply(bitmapTransform(new RoundedCornersTransformation(radius, 0,
                        RoundedCornersTransformation.CornerType.ALL)))
                .apply(new RequestOptions().placeholder(id))
                .transition(withCrossFade())
                .into(imageView);
        return this;
    }


    public ViewHolder setImage(int viewId, Bitmap bitmap) {
        ImageView imageView = getView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    public ViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public ViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public ViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public ViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(context.getResources().getColor(textColorRes));
        return this;
    }

    public ViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public ViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public ViewHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public ViewHolder setTypeface(int viewId, Typeface typeface) {
        TextView view = getView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    public ViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public ViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public ViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public ViewHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public ViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public ViewHolder setOnCheckedChangeListener(int viewId, CompoundButton.OnCheckedChangeListener listener) {
        CompoundButton view = getView(viewId);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

    public ViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public ViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public ViewHolder setChecked(int viewId, boolean checked) {
        View view = getView(viewId);
        // View unable cast to Checkable
        if (view instanceof CompoundButton) {
            ((CompoundButton) view).setChecked(checked);
        } else if (view instanceof CheckedTextView) {
            ((CheckedTextView) view).setChecked(checked);
        }
        return this;
    }
}
