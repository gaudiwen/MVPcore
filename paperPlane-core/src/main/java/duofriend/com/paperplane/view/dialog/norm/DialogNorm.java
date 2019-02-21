package duofriend.com.paperplane.view.dialog.norm;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialog;
import android.view.View;

import duofriend.com.paperplane.R;
import duofriend.com.paperplane.view.dialog.DialogBuilder;
import duofriend.com.paperplane.view.dialog.QuickDialog;

/**
 * Created by Yzq on 2018/10/31 0031 17:16
 * Email: 277202583@qq.com
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * ┃　　　┃
 * ┃　　　┃
 * ┃　　　┗━━━┓
 * ┃　　　　　　　┣┓
 * ┃　　　　　　　┏┛
 * ┗┓┓┏━┳┓┏┛
 * ┃┫┫　┃┫┫
 * ┗┻┛　┗┻┛
 * Buddha bless, never BUG
 */
public class DialogNorm extends AppCompatDialog {
    private View.OnClickListener leftClick;
    private View.OnClickListener rightClick;
    private String content;
    private QuickDialog mQuickDialog;

    public DialogNorm(Context context, String content, View.OnClickListener leftClick , View.OnClickListener rightClick) {
        super(context);
        this.content =content;
        this.leftClick = leftClick;
        this.rightClick = rightClick;
        initDialog(context);
    }

    private void initDialog(Context context) {
        mQuickDialog = DialogBuilder.create(context).setContentView(R.layout.dialog_norm).setText(R.id.tv_phone, content).setOnClickListener(R.id.tv_cancel, leftClick).setOnClickListener(R.id.tv_confirm, rightClick).create();
    }

    public void showDialog(){
        mQuickDialog.show();
    }


}
