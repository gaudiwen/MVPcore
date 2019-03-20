package duofriend.com.paperplane.utils.imagePick;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;
import java.io.Serializable;

import duofriend.com.paperplane.R;

/**
 * Created  on 2018/8/3 0003 19:18
 * Email: 277202583@qq.com
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * Buddha bless, never BUG
 */
public class GlideImageLoader implements ImageLoader, Serializable {


    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity).load(Uri.fromFile(new File(path))).apply(new RequestOptions().fallback(R.drawable.ic_default_image).placeholder(R.drawable.ic_default_image).diskCacheStrategy(DiskCacheStrategy.ALL)).into(imageView);
    }

    public void displayImage(Fragment fragment, String path, ImageView imageView, int width, int height) {
        Glide.with(fragment).load(Uri.fromFile(new File(path))).apply(new RequestOptions().fallback(R.drawable.ic_default_image).placeholder(R.drawable.ic_default_image).diskCacheStrategy(DiskCacheStrategy.ALL)).into(imageView);
    }

    public void displayImage(Fragment fragment, String path, ImageView imageView) {
        Glide.with(fragment).load(Uri.fromFile(new File(path))).apply(new RequestOptions().fallback(R.drawable.ic_default_image).placeholder(R.drawable.ic_default_image).diskCacheStrategy(DiskCacheStrategy.ALL)).into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity).load(Uri.fromFile(new File(path))).apply(new RequestOptions().fallback(R.drawable.ic_default_image).placeholder(R.drawable.ic_default_image).diskCacheStrategy(DiskCacheStrategy.ALL)).into(imageView);
    }

    public void displayImagePreview(Fragment fragment, String path, ImageView imageView, int width, int height) {
        Glide.with(fragment).load(Uri.fromFile(new File(path))).apply(new RequestOptions().fallback(R.drawable.ic_default_image).placeholder(R.drawable.ic_default_image).diskCacheStrategy(DiskCacheStrategy.ALL)).into(imageView);
    }

    @Override
    public void clearMemoryCache() {
    }
}
