package com.example.rxjava_retrofit_mvp_md.delegates;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

public abstract class BaseDelegate extends SwipeBackFragment {

    private Unbinder mBind;
    private View mRootView;
    private Toolbar mToolbar;
    private AppCompatTextView mTvTitle;
    protected CompositeDisposable mDisposable;
    private View mIvLeft;


    protected abstract Object setLayout();

    protected abstract void initData(Bundle arguments);

    protected abstract void init();

//    public abstract void onBindView(@Nullable Bundle savedInstanceState,View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = getActivity().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.3f;
        window.setAttributes(windowParams);
        if(setLayout() instanceof Integer){
            mRootView = inflater.inflate((Integer)setLayout(),container,false);
        }else if(setLayout() instanceof View){
            mRootView = (View)setLayout();
        }else {
            throw  new RuntimeException("setLayout must be View or int");
        }
        if(mRootView != null){
            mBind = ButterKnife.bind(this, mRootView);
            initToolbar();
            setHasOptionsMenu(true);
//            onBindView(savedInstanceState,rootView);
        }else {
            throw new IllegalArgumentException("can’t find layout");
        }
        //设置右滑返回的触摸区域
//        setEdgeLevel(ConvertUtils.dp2px(100));
        //使用触摸返回就返回这个
        return attachToSwipeBack(mRootView);
//        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDisposable = new CompositeDisposable();
        initData(getArguments());
      //  LogUtils.d("baseDelegate",this.getClass().getName());
        setParallaxOffset(0.5f);
        init();
    }

    protected View getRootView(){
        return mRootView;
    }

    private void initToolbar() {
    }

    //退出时的时间
    private long mExitTime;

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) < 400) {

            mExitTime = System.currentTimeMillis();
        } else {
            onToolBarBack();
           // System.gc();
            //System.exit(0);
        }
    }

    /**
     *  双击toolBar让列表返回顶部
     */
    protected void onToolbarDoubleClick(View v){

    }
    public void removeBackMenu(){
        if(mIvLeft != null){
            mIvLeft.setVisibility(View.GONE);
        }
//        mToolbar.setNavigationIcon(null);
    }

    public void setTitle(String title){
        if(mTvTitle != null){
            mTvTitle.setText(title);
        }
    }

    public void setTitle(@StringRes int resId){
        if(mTvTitle != null){
            mTvTitle.setText(resId);
        }
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
    public void setUpToolbar(Toolbar toolbar, @NonNull String title, @DrawableRes int navigation, boolean hasBack) {
        if (null == toolbar) {
            return;
        }
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        if (!TextUtils.isEmpty(title)) {
            getActivity().setTitle(title);
        }
       /* toolbar.setNavigationOnClickListener(view -> onToolBarBack());
        if (hasBack) {
            toolbar.setNavigationIcon(navigation != 0 ? navigation : R.drawable.ic_arrow_back_black_24dp);
            toolbar.setNavigationOnClickListener(view -> onToolBarBack());
        }*/
    }

    protected void onToolBarBack(){
        getActivity().onBackPressed();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mBind != null){
            mBind.unbind();
        }
        if(mDisposable!=null){
            mDisposable.dispose();
            mDisposable.clear();
        }
    }

    /**
     * true 为软键盘显示
     * @return
     */
    protected boolean isSoftShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = getActivity().getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
//        LogUtils.d("isSoftShowing","screenHeight:"+screenHeight);
//        LogUtils.d("isSoftShowing","rect.bottom:"+rect.bottom);
//        LogUtils.d("isSoftShowing","screenHeight - rect.bottom:"+(screenHeight - rect.bottom));
        // 华为测试的是screenHeight:2160，view 的rect.bottom是2032，出现软键盘rect.bottom 是1272差 888
        //小米测试的是screenHeight:2280，view 的rect.bottom 2150，出现软键盘rect.bottom 是1312，差968
        return screenHeight - rect.bottom >250;
    }

    public class OnLimitClickHelper implements View.OnClickListener {
         static final int LIMIT_TIME = 400;
         long lastClickTime = 0;
         OnLimitClickListener onLimitClickListener = null;

        public OnLimitClickHelper(OnLimitClickListener onLimitClickListener){
            this.onLimitClickListener = onLimitClickListener;
        }

        @Override
        public void onClick(View v) {
            long curTime = Calendar.getInstance().getTimeInMillis();
            if (curTime - lastClickTime > LIMIT_TIME) {
                lastClickTime = curTime;
                if(onLimitClickListener != null){
                    onLimitClickListener.onClick(v);
                }
            }else {
                //ToastUtil.getInstance().showToast("没用的亲");
            }
        }
    }

    public interface OnLimitClickListener {

        /**
         * 按钮点击事件
         * @param view
         */
        void onClick(View view);

    }

}
