package duofriend.com.paperplane.view.popup.PopupWindow;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.IntDef;
import android.view.View;
import android.widget.PopupWindow;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import duofriend.com.paperplane.R;
import duofriend.com.paperplane.view.popup.WindowManager.WindowUtil;


/**
 * Created by MQ on 2017/5/2.
 */

public class CommonPopupWindow extends PopupWindow {
    final PopupController controller;

    @Override
    public int getWidth() {
        return controller.mPopupView.getMeasuredWidth();
    }

    @Override
    public int getHeight() {
        return controller.mPopupView.getMeasuredHeight();
    }

    public interface ViewInterface {
        void getChildView(View view, int layoutResId);
    }

    private CommonPopupWindow(Context context) {
        controller = new PopupController(context, this);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        controller.setBackGroundLevel(1.0f);
    }

    public static class Builder {
        private final PopupController.PopupParams params;
        private ViewInterface listener;

        public Builder(Context context) {
            params = new PopupController.PopupParams(context);
        }

        /**
         * @param layoutResId 设置PopupWindow 布局ID
         * @return Builder
         */
        public Builder setView(int layoutResId) {
            params.mView = null;
            params.layoutResId = layoutResId;
            return this;
        }

        /**
         * @param view 设置PopupWindow布局
         * @return Builder
         */
        public Builder setView(View view) {
            params.mView = view;
            params.layoutResId = 0;
            return this;
        }

        /**
         * 设置子View
         *
         * @param listener ViewInterface
         * @return Builder
         */
        public Builder setViewOnclickListener(ViewInterface listener) {
            this.listener = listener;
            return this;
        }

        /**
         * 设置宽度和高度 如果不设置 默认是wrap_content
         *
         * @param width 宽
         * @return Builder
         */
        public Builder setWidthAndHeight(int width, int height) {
            params.mWidth = width;
            params.mHeight = height;
            return this;
        }

        /**
         * 设置背景灰色程度
         *
         * @param level 0.0f-1.0f
         * @return Builder
         */
        public Builder setBackGroundLevel(float level) {
            params.isShowBg = true;
            params.bg_level = level;
            return this;
        }

        /**
         * 是否可点击Outside消失
         *
         * @param touchable 是否可点击
         * @return Builder
         */
        public Builder setOutsideTouchable(boolean touchable) {
            params.isTouchable = touchable;
            return this;
        }
/*
        public Builder setAnimationStyle(int animationStyle) {
            params.isShowAnim = true;
            params.animationStyle = animationStyle;
            return this;
        }*/


        public static final int LEFT = 101;
        public static final int RIGHT = 102;
        public static final int DOWN = 103;
        public static final int UP = 104;
        @IntDef({LEFT, RIGHT,DOWN,UP}) //限定为MAN,WOMEN
        @Retention(RetentionPolicy.SOURCE) //表示注解所存活的时间,在运行时,而不会存在. class 文件.
        public @interface AnimStyle { //接口，定义新的注解类型
        }

        public Builder setAnimationStyle(@AnimStyle int anim){
            params.isShowAnim = true;
            switch (anim){
                case LEFT:
                    params.animationStyle = R.style.AnimHorizontal;
                    break;
                case RIGHT:
                    params.animationStyle = R.style.AnimRight;
                    break;
                case DOWN:
                    params.animationStyle = R.style.AnimDown;
                    break;
                case UP:
                    params.animationStyle = R.style.AnimUp;
                    break;
            }
            return this;
        }

        public CommonPopupWindow create() {
            final CommonPopupWindow popupWindow = new CommonPopupWindow(params.mContext);
            params.apply(popupWindow.controller);
            if (listener != null && params.layoutResId != 0) {
                listener.getChildView(popupWindow.controller.mPopupView, params.layoutResId);
            }
            WindowUtil.measureWidthAndHeight(popupWindow.controller.mPopupView);
            return popupWindow;
        }
    }
    @Override
    public void showAsDropDown(View anchor) {
        if(Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }


}
