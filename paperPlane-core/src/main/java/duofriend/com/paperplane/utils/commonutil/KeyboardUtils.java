package duofriend.com.paperplane.utils.commonutil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/08/02
 *     desc  : 键盘相关工具类
 * </pre>
 */
public final class KeyboardUtils {

    private KeyboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /*
      避免输入法面板遮挡
      <p>在manifest.xml中activity中设置</p>
      <p>android:windowSoftInputMode="adjustPan"</p>
     */

    /**
     * 动态显示软键盘
     *
     * @param activity activity
     */
    public static void showSoftInput(final Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 动态显示软键盘
     *
     * @param view 视图
     */
    @SuppressLint("CheckResult")
    public static void showSoftInput(final View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        if(view instanceof EditText){
            EditText editText = (EditText) view;
            editText.setSelection(editText.getText().toString().trim().length());
        }
        Observable.timer(100,TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    InputMethodManager imm = (InputMethodManager) Utils.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm == null){
                        return;
                    }
                    imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
                });
    }


    /**
     * 动态隐藏软键盘
     *
     * @param activity activity
     */
    public static void hideSoftInput(final Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 动态隐藏软键盘
     *
     * @param view 视图
     */
    public static void hideSoftInput(final View view) {
        InputMethodManager imm = (InputMethodManager) Utils.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 切换键盘显示与否状态
     */
    public static void toggleSoftInput() {
        InputMethodManager imm = (InputMethodManager) Utils.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /**
     * 点击屏幕空白区域隐藏软键盘
     * <p>根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘</p>
     * <p>需重写dispatchTouchEvent</p>
     * <p>参照以下注释代码</p>
     */
    public static void clickBlankArea2HideSoftInput() {
        LogUtils.d("tips", "U should copy the following code.");
        /*
        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();
                if (isShouldHideKeyboard(v, ev)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
            return super.dispatchTouchEvent(ev);
        }

        // 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘
        private boolean isShouldHideKeyboard(View v, MotionEvent event) {
            if (v != null && (v instanceof EditText)) {
                int[] l = {0, 0};
                v.getLocationInWindow(l);
                int left = l[0],
                        top = l[1],
                        bottom = top + v.getHeight(),
                        right = left + v.getWidth();
                return !(event.getX() > left && event.getX() < right
                        && event.getY() > top && event.getY() < bottom);
            }
            return false;
        }
        */
    }
    public static void TimerHideKeyboard(final View v)
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run()
            {
                InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
                if ( imm.isActive( ) )
                {
                    imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );
                }
            }
        }, 10);
    }

    /**
     * 获取软键盘状态
     * @param context
     * @return
     */
    public static boolean isShowSoftInput(Context context,View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        //获取状态信息
        return imm.isActive(view);//true 打开
    }
}
