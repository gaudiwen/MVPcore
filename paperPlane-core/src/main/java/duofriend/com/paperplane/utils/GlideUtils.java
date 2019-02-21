package duofriend.com.paperplane.utils;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.io.File;

/**
 * Created by wzb on 2017/11/23 0023.
 */

public class GlideUtils {
    /**
     * glide 不使用磁盘缓存
     */
   private RequestOptions noneCacheOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE);
    private final RequestOptions mIconOptions;
    private final RequestOptions mCircleOptions;

    private GlideUtils(){
        mIconOptions = new RequestOptions()
                .fallback(duofriend.com.paperplane.R.drawable.ic_default_image)
                .placeholder(duofriend.com.paperplane.R.drawable.ic_default_image);
        mCircleOptions = new RequestOptions().apply(RequestOptions.bitmapTransform(new CircleCrop()));
    }

   private static class GlideUtilsHelper{
       private static final GlideUtils INSTANCE=new GlideUtils();
   }

   public static GlideUtils getInstance(){
       return GlideUtilsHelper.INSTANCE;
   }

   public RequestOptions noneCacheOpt(){
       return this.noneCacheOptions;
   }
    private RequestOptions allCacheOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);

    public RequestOptions getAllCacheOptions() {
        return allCacheOptions;
    }

    public void loadIcon(Fragment fragment, String path, final ImageView imageView) {
        Glide.with(fragment)
                .load(Uri.fromFile(new File(path)))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (imageView == null) {
                            return false;
                        }
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                        float scale = (float) vw / (float) resource.getIntrinsicWidth();
                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                        params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                        imageView.setLayoutParams(params);
                        return false;
                    }
                })
                .apply(mCircleOptions)
                .into(imageView);
    }

}
