package com.example.yang.mytest.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yang.mytest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IpDialog extends Dialog {
    @BindView(R.id.yes) Button yes;
    @BindView(R.id.no) Button no;
    @BindView(R.id.dialog_title) TextView titleTv;
    @BindView(R.id.dialog_ip) EditText ipEt;

    /*  -------------------------------- 接口监听 -------------------------------------  */

    private onNoOnclickListener noOnclickListener;
    private onYesOnclickListener yesOnclickListener;

    public interface onYesOnclickListener {
        void onYesClick();
    }

    public interface onNoOnclickListener {
        void onNoClick();
    }

    public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            no.setText(str);
        }
        this.noOnclickListener = onNoOnclickListener;
    }

    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yes.setText(str);
        }
        this.yesOnclickListener = onYesOnclickListener;
    }

    /*  ---------------------------------- 构造方法 -------------------------------------  */

    public IpDialog(Context context) {
        super(context, R.style.MyDialog);
        View  view = View.inflate(context, R.layout.ip_dialog, null);//风格主题
        setContentView(view);
        ButterKnife.bind(this,view);
    }


    /*  ---------------------------------- onCreate-------------------------------------  */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //按空白处不能取消动画
//        setCanceledOnTouchOutside(false);

        //初始化界面控件的事件
        initEvent();

    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }

    /*  ---------------------------------- set方法 传值-------------------------------------  */
//为外界设置一些public 公开的方法，来向自定义的dialog传递值
    public void setTitle(String title) {
        titleTv.setText(title);
    }

    public void setIp(String ip) {
        ipEt.setText(ip);
    }

    public String getIp() {
        return ipEt.getText().toString();
    }

}
